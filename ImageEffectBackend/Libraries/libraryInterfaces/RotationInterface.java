package libraryInterfaces;

public class RotationInterface {
    static {
        String libraryPath = System.getProperty("user.dir") + "/ImageEffectBackend/Libraries/RotationLibrary/RotationLib.so";
        System.load(libraryPath);
    }

    public static native Pixel[][] applyRotation(Pixel[][] image, int value);
}
