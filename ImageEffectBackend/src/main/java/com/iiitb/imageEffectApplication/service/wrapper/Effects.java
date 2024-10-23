package com.iiitb.imageEffectApplication.service.wrapper;

import com.iiitb.imageEffectApplication.baseEffects.*;
import com.iiitb.imageEffectApplication.exception.IllegalParameterException;
import com.iiitb.imageEffectApplication.service.LoggingService;
import libraryInterfaces.*;
import libraryInterfaces.Pixel;

public class Effects {
    private Pixel[][] inputImage;
    private String imageName;
    private LoggingService loggingService;
    private String type;

    // Effects class is a super class used to store all the data which is common for all the effects with effect name
    public Effects(Pixel[][] image, String imageName, String type) {
        this.inputImage = image;
        this.imageName = imageName;
        this.loggingService = new LoggingService();
        this.type = type;
    }

    // change function will apply all the effects which doesn't have any parameterized or discrete value
    // Ex: Grayscale ,Invert ,Sepia,Dominant
    public Pixel[][] change() {
        //Normal class defines a class with effects having no parameterized values
        class Normal implements PhotoEffect {
            @Override
            //to apply the effects respectively of their types from the jni
            public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService) {

                if(type.equals("grayscale")) {
                    return GrayscaleInterface.applyGrayscale(inputImage);
                }
                else if(type.equals("dominant")){
                    return DominantColourInterface.applyDominantColour(inputImage);
                }
                else if(type.equals("invert")){
                    return InvertInterface.applyInvert(inputImage);
                }
                else if(type.equals("sepia")){
                    return SepiaInterface.applySepia(inputImage);
                }
                return image;
            }


        }

        Normal effect = new Normal();
        //to add the effect of the log to the file
        try {
            loggingService.addLog(imageName, type, "-");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return effect.apply(inputImage, imageName, loggingService);
    }
    // this change function will apply all the effects which have only 1  parameteric value
    // Ex: Brightness, Contrast ,Sharpen , Gaussian
    public Pixel[][] change(float amount) {
        // SingleValue class as the name suggest depends only on 1 parameter
        class SingleValue implements SingleValueParameterizableEffect {
            private float amount;

            SingleValue(float amount){
                this.amount = amount;
            }
            @Override
            //to set the parameter value of the amount by which effect has to be changed
            public void setParameterValue( float value) throws IllegalParameterException {
                //System.out.println(paramName + " " + value);
                amount = value;
            }
            @Override
            //to apply the effects respectively of their types from the jni
            public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService) {
                if (type.equals("brightness")) {
                    return BrightnessInterface.applyBrightness(image, amount);
                } else if (type.equals("contrast")) {
                    return ContrastInterface.applyContrast(image, amount);

                } else if (type.equals("sharpen")) {
                    return SharpenInterface.applySharpen(image,amount);
                }else if (type.equals("gaussian")) {
                    return GaussianBlurInterface.applyGaussianBlur(image,amount);
                }

                return image;
            }


        }

        SingleValue effect;
        // this is used to create logs of different effects with their amount and parameter name
        if(type.equals("brightness")) {
            effect = new SingleValue(amount);
            try {
                loggingService.addLog(imageName, type,"brightness-"+amount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("contrast")){
            effect = new SingleValue(amount);
            try {
                loggingService.addLog(imageName, type,"contrast-"+amount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("sharpen")){
            try {
                loggingService.addLog(imageName, type,"sharpen-"+amount);
            } catch (Exception e) {
                e.printStackTrace();
            }
            effect = new SingleValue(amount);
        }
        else if(type.equals("gaussian")){
            try {
                loggingService.addLog(imageName, type,"gaussian-"+amount);
            } catch (Exception e) {
                e.printStackTrace();
            }
            effect = new SingleValue(amount);
        }
        else {
            effect = new SingleValue(0);
        }

        try {
            effect.setParameterValue(amount);

        } catch (IllegalParameterException e) {
            e.printStackTrace();
        }

        return effect.apply(inputImage, imageName, loggingService);
    }
    // this change function will apply all the effects which have only 2  parameteric value
    // Ex: Hue Saturation
    public Pixel[][] change(float amount, float amountTwo) {
        // class Parameterized is used to apply effect with 2 parametric value
        class Parameterized implements ParameterizableEffect {
            private float hueParameter;
            private float saturationParameter;



            @Override
            //setParameter is used to set value to the respective effect name ex hue and saturation
            public void setParameter(String paramName, float value) throws IllegalParameterException {
                switch (paramName) {
                    case "hue":
                        hueParameter = value;
                        break;
                    case "saturation":
                        saturationParameter = value;
                        break;
                    default:
                        throw new IllegalParameterException("Unsupported parameter: " + paramName);
                }
            }


            @Override
            //to apply the effects respectively of their types from the jni
            public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService) {
                if (type.equals("hueSaturation")) {
                    return HueSaturationInterface.applyHueSaturation(inputImage,saturationParameter,hueParameter);
                }

                return image;
            }


        }

        Parameterized effect = new Parameterized();
        // this is used to create logs of effect with their amount and parameter name
        try {
                loggingService.addLog(imageName, type,"saturation-"+amount+",hue-"+amountTwo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        try {
            effect.setParameter("hue",amountTwo);
            effect.setParameter("saturation",amount);
        } catch (IllegalParameterException e) {
            e.printStackTrace();
        }
        return effect.apply(inputImage, imageName, loggingService);
    }
    // this change function will apply all the effects which have only 1 discrete value
    // Ex: Rotation
    public Pixel[][] changeDiscrete(int amount) {
        class Discrete implements SingleValueDiscreteEffect {

            private int rotationParameter;

            @Override
            //setParameterValue is used to assign value for the amount by which effect is applied
            public void setParameterValue(int parameterValue) throws IllegalParameterException{
               // System.out.println(optionName + " " + value);
                if(type.equals("rotation") && parameterValue >= 0 && parameterValue < 4) {
                    rotationParameter = parameterValue;
                }
                else {
                    throw new IllegalParameterException("Invalid parameter: " + parameterValue);
                }
            }


            @Override
            //to apply the effects respectively of their types from the jni
            public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService) {

                if (type.equals("rotation")) {
                    System.out.println(rotationParameter);
                    return RotationInterface.applyRotation(image, rotationParameter);
                }
                return image;
            }


        }
        Discrete effect = new Discrete();

        // this is used to create logs of effect with their amount and parameter name
        try {
            if (type.equals("rotation")){
                try {
                    String a = "";
                    if(amount == 0){
                        a = "0°";
                    }
                    else if(amount == 1){
                        a = "90°";
                    }
                    else if(amount == 2){
                        a = "180°";
                    }
                    else if(amount == 3){
                        a = "270°";
                    }
                    loggingService.addLog(imageName, type,a);
                } catch (Exception e) {
                    System.out.println("Effect not available");
                    e.printStackTrace();
                }
                effect.setParameterValue(amount);
            }


        } catch (IllegalParameterException e) {
            System.out.println("Invalid Parameter");
            e.printStackTrace();
        }
        return effect.apply(inputImage, imageName, loggingService);

    }

    // this change function will apply all the effects which have only 2 discrete value
    // Ex: Flip
    public Pixel[][] changeDiscrete(int amount, int amountTwo) {
        //Discrete class is used to apply the effect with discrete values
        class Discrete implements DiscreteEffect {
            private int verticalParameter;
            private int horizontalParameter;

            @Override
            //selectOptionValue function is used to assign effect type to their respective values
            public void selectOptionValue(String optionName, int value) throws IllegalParameterException{
                //System.out.println(optionName + " " + value);
                switch (optionName) {
                    case "vertical":
                        verticalParameter = value;
                        break;
                    case "horizontal":
                        horizontalParameter = value;
                        break;
                    default:
                        throw new IllegalParameterException("Unsupported parameter: " + optionName);
                }
            }


            @Override
            //to apply the effects respectively of their types from the jni
            public Pixel[][] apply(Pixel[][] image, String fileName, LoggingService loggingService) {
                if (type.equals("flip")) {
                    return FlipInterface.applyFlip(image,horizontalParameter,verticalParameter);
                }
                return image;
            }


        }
        Discrete effect = new Discrete();
        // this is used to create logs of effect with their amount and parameter name

        try {

            if(type.equals("flip")){
                try {
                    String a1,a2 = "";
                    if(amount == 1){
                        a1 = "True";
                    }
                    else{
                        a1 = "False";
                    }
                    if(amountTwo == 1){
                        a2 = "True";
                    }
                    else{
                        a2 = "False";
                    }

                    loggingService.addLog(imageName, type,"Horizantal-"+a1+",Vertical-"+a2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                effect.selectOptionValue("horizontal",amount);
                effect.selectOptionValue("vertical",amountTwo);
            }
        } catch (IllegalParameterException e) {
            e.printStackTrace();
        }
     return effect.apply(inputImage, imageName, loggingService);

    }

}

