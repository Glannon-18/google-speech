package com.synthesis;// Imports the Google Cloud client library

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Google Cloud TextToSpeech API sample application. Example usage: mvn package exec:java
 * -Dexec.mainClass='com.example.texttospeech.QuickstartSample'
 */
public class Synthesis {

    /**
     * Demonstrates using the Text to Speech client to list the client's supported voices.
     *
     * @throws Exception on TextToSpeechClient Errors.
     */
    public static List<Voice> listAllSupportedVoices() throws Exception {
        // Instantiates a client
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Builds the text to speech list voices request
            ListVoicesRequest request = ListVoicesRequest.getDefaultInstance();

            // Performs the list voices request
            ListVoicesResponse response = textToSpeechClient.listVoices(request);
            List<Voice> voices = response.getVoicesList();

            for (Voice voice : voices) {
                // Display the voice's name. Example: tpc-vocoded
                System.out.format("Name: %s\n", voice.getName());

                // Display the supported language codes for this voice. Example: "en-us"
                List<ByteString> languageCodes = voice.getLanguageCodesList().asByteStringList();
                for (ByteString languageCode : languageCodes) {
                    System.out.format("Supported Language: %s\n", languageCode.toStringUtf8());
                }

                // Display the SSML Voice Gender
                System.out.format("SSML Voice Gender: %s\n", voice.getSsmlGender());

                // Display the natural sample rate hertz for this voice. Example: 24000
                System.out.format("Natural Sample Rate Hertz: %s\n\n", voice.getNaturalSampleRateHertz());
            }
            return voices;
        }
    }

    //    越南文 vi-VN
//    泰文 th-TH
//    中文 cmn-CN
    public static void text2speech(String text, String language) throws IOException {

        System.out.println("语音合成语言：" + language + "，文字内容：" + text);
        // Instantiates a client
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            // Build the voice request, select the language code ("en-US") and the ssml voice gender
            // ("neutral")
            VoiceSelectionParams voice =
                    VoiceSelectionParams.newBuilder()
//                            .setLanguageCode("en-US")
                            .setLanguageCode(language)
                            .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                            .build();

            // Select the type of audio file you want returned
            AudioConfig audioConfig =
                    AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();

            // Perform the text-to-speech request on the text input with the selected voice parameters and
            // audio file type
            SynthesizeSpeechResponse response =
                    textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();

            // Write the response to the output file.
            try (OutputStream out = new FileOutputStream("d:\\output.mp3")) {
                out.write(audioContents.toByteArray());
                System.out.println("Audio content written to file \"output.mp3\"");
            }
        }
    }


    public static void main(String... args) throws Exception {
        text2speech("ဗီယက်နမ်", "my-MM");

    }

}