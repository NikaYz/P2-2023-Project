package libraryInterfaces;

public class GaussianBlurInterface {
    static {
        String libraryPath = System.getProperty("user.dir") + "/ImageEffectBackend/Libraries/GaussianBlurLibrary/GaussianBlurLib.so";
        System.load(libraryPath);
    }

    public static native Pixel[][] applyGaussianBlur(Pixel[][] image, float radius);
}
