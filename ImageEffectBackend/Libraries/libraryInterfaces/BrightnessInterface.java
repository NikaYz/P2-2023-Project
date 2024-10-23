package libraryInterfaces;

public class BrightnessInterface {
    static {
        String libraryPath = System.getProperty("user.dir") + "/ImageEffectBackend/Libraries/BrightnessLibrary/BrightnessLib.so";
        System.load(libraryPath);
    }

    public static native Pixel[][] applyBrightness(Pixel[][] image, float amount);
}
