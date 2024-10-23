package com.iiitb.imageEffectApplication.service;
import com.iiitb.imageEffectApplication.utils.ProcessingUtils;

import com.iiitb.imageEffectApplication.service.wrapper.Effects;
import com.iiitb.imageEffectApplication.baseEffects.ParameterizableEffect;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import libraryInterfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class PhotoEffectService {

    @Autowired
    private ProcessingUtils processingUtils;

    @Autowired
    private LoggingService loggingService;
// in this we have used Effects class from wrapper interface to apply effects
    public ResponseEntity<byte[]> applyHueSaturationEffect(float hueAmount, float saturationAmount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();


            // ACTUAL WORK STARTS HERE

            // TODO
            Effects hueSaturation = new Effects(inputImage,imageName,"hueSaturation");

            Pixel[][] modifiedImage = hueSaturation.change(saturationAmount,hueAmount); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE


            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyBrightnessEffect(float amount, MultipartFile imageFile) {
        try {
            System.out.println(imageFile);
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();
            LoggingService ls = new LoggingService();

            // ACTUAL WORK STARTS HERE
            // TODO

            Effects brightness = new Effects(inputImage,imageName,"brightness");
            Pixel[][] modifiedImage = brightness.change(amount);

            // ACTUAL WORK ENDS HERE



            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyContrastEffect(float amount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();
            LoggingService ls = new LoggingService();


            // ACTUAL WORK STARTS HERE

            // TODO

            Effects brightness = new Effects(inputImage,imageName,"contrast");
            Pixel[][] modifiedImage = brightness.change(amount);

            // ACTUAL WORK ENDS HERE



            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyFlipEffect(MultipartFile imageFile, int horizontalFlipValue, int verticalFlipValue) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();



            // ACTUAL WORK STARTS HERE

            // TODO
            Effects flip = new Effects(inputImage,imageName,"flip");
            Pixel[][] modifiedImage = flip.changeDiscrete(horizontalFlipValue,verticalFlipValue);

            // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE




            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyGaussianBlurEffect(float radius, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();


            // ACTUAL WORK STARTS HERE
            System.out.println(radius);
            // TODO
            Effects gaussian = new Effects(inputImage,imageName,"gaussian");
            Pixel[][] modifiedImage = gaussian.change(radius); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE



            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyGrayscaleEffect(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

//
            // ACTUAL WORK STARTS HERE
             // TODO
            Effects Grayscale = new Effects(inputImage,imageName,"grayscale");

            Pixel[][] modifiedImage = Grayscale.change(); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyInvertEffect(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();
            // TODO
            Effects Invert = new Effects(inputImage,imageName,"invert");
            Pixel[][] modifiedImage = Invert.change(); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applyRotationEffect(int value, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();


            // ACTUAL WORK STARTS HERE

            // TODO
            //Pixel[][] modifiedImage = RotationInterface.applyRotation(inputImage,value);
            Effects rotation = new Effects(inputImage,imageName,"rotation");
            Pixel[][] modifiedImage = rotation.changeDiscrete(value);

            // ACTUAL WORK ENDS HERE


            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applySepiaEffect(MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // TODO
            Effects Sepia = new Effects(inputImage,imageName,"sepia");

            Pixel[][] modifiedImage = Sepia.change(); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> applySharpenEffect(float amount, MultipartFile imageFile) {
        try {
            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE

            // TODO
            Effects Sharpen = new Effects(inputImage,imageName,"sharpen");
            Pixel[][] modifiedImage = Sharpen.change(amount); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<byte[]> getDominantColour(MultipartFile imageFile) {
        try {

            Pixel[][] inputImage = processingUtils.preprocessing(imageFile);
            String imageName = imageFile.getOriginalFilename();

            // ACTUAL WORK STARTS HERE

            // TODO

            Effects Dominant = new Effects(inputImage,imageName,"dominant");
            Pixel[][] modifiedImage = Dominant.change(); // Replace this with actual modified image

            // ACTUAL WORK ENDS HERE

            return processingUtils.postProcessing(modifiedImage);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
