package com.translate;

import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;
import com.google.cloud.translate.testing.RemoteTranslateHelper;

import java.util.List;

public class Google_Translate {


    public static String translate(String text, String sourceLanguage, String targetLanguage) {

        RemoteTranslateHelper helper = RemoteTranslateHelper.create();
        Translate translate = helper.getOptions().getService();
        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage(sourceLanguage),
                        Translate.TranslateOption.targetLanguage(targetLanguage),
                        // Use "base" for standard edition, "nmt" for the premium model.
                        Translate.TranslateOption.model("nmt"));

        System.out.printf("TranslatedText:\nText: %s\n", translation.getTranslatedText());
        return translation.getTranslatedText();
    }

    public static void getLanguage(){

        RemoteTranslateHelper helper = RemoteTranslateHelper.create();
        Translate translate = helper.getOptions().getService();
        List<Language> languages = translate.listSupportedLanguages();

        for (Language language : languages) {
            System.out.printf("Name: %s, Code: %s\n", language.getName(), language.getCode());
        }
    }


    public static void main(String[] args) {
        Google_Translate.translate("特朗普制裁华为", "zh", "th");
//        getLanguage();
    }


}
