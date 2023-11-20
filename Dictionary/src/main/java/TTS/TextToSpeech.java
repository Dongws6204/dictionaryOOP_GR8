package TTS;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    public static void main(String[] args) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();

        // Chọn một giọng nói từ danh sách (nếu có)
        Voice selectedVoice = null;
        for (Voice voice : voices) {
            if (voice.getName().equals("kevin")) { // Đổi thành tên giọng nói tương ứng
                selectedVoice = voice;
                break;
            }
        }

        if (selectedVoice != null) {
            selectedVoice.allocate();
            String text = "Hello, I'm using FreeTTS in IntelliJ IDEA.";
            selectedVoice.speak(text);
            selectedVoice.deallocate();
        } else {
            System.out.println("Không tìm thấy giọng nói.");
        }
    }
}
