import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LogModel } from '../_models/LogModel';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  private baseUrl: String = environment.rootUrl + "/logs"
  constructor(private http: HttpClient) { }

  getAllLogs(): Observable<LogModel[]> {
    return this.http.get<LogModel[]>(`${this.baseUrl}`);
  }

  getLogsByEffect(effectName: string): Observable<LogModel[]> {
    return this.http.get<LogModel[]>(`${this.baseUrl}/${effectName}`);
  }
  // we have added this function to add timestamp filter (use to send http request to backend)
  getLogsBetweenTimestamp(startTime: string, endTime: string): Observable<LogModel[]> {
    const url = `${this.baseUrl}/timestamp/${startTime},${endTime}`;
    return this.http.get<LogModel[]>(url);
  }
  clearLogs(): Observable<string> {
    return this.http.delete(`${this.baseUrl}`, {responseType: "text"});
  }
}
