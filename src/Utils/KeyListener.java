package Utils;

import javafx.scene.Scene;

import java.util.ArrayList;

public class KeyListener {
    private ArrayList<String> inputs;

    public KeyListener(Scene scene) {
        this.inputs = new ArrayList<>();

        // Detecting the key inputs
        {
            scene.setOnKeyPressed(event -> {
                String code = event.getCode().toString();
                if (!inputs.contains(code)) {
                    inputs.add(code);
                }
            });

            scene.setOnKeyReleased(event -> {
                String code = event.getCode().toString();
                inputs.remove(code);
            });
        }
    }

    public ArrayList<String> getInputs() {
        return inputs;
    }
}
