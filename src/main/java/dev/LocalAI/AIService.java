package dev.LocalAI;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.LlamaOutput;
import de.kherud.llama.ModelParameters;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final LlamaModel model;

    public AIService() {
        // Configure path to your local Qwen 0.5B GGFU model file
        ModelParameters modelParameters = new ModelParameters()
                .setModel("/home/harsh/ollama/AImodels/qwen2.5-0.5b-instruct-q4_k_m.gguf")
                .setThreads(4);

        this.model = new LlamaModel(modelParameters);
    }

    public String generate(String prompt) {
        InferenceParameters params = new InferenceParameters(prompt)
                .setNPredict(512)
                .setTemperature(0.7f);

        StringBuilder sb = new StringBuilder();
        Iterable<LlamaOutput> outputs = model.generate(params);
        for (LlamaOutput output : outputs) {
            sb.append(output.text);
        }
        return sb.toString();
    }
}