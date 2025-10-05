package com.github.yuki.iwaya.service;

import software.amazon.awssdk.services.bedrock.BedrockClient;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;

import java.util.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class AmazonBedrockService {

    BedrockClient bedrockClient = BedrockClient.builder()
        .region(Region.AP_NORTHEAST_1)
        .build();


    BedrockRuntimeClient bedrockRuntimeClient = BedrockRuntimeClient.builder()
        .region(Region.AP_NORTHEAST_1)
        .build();

    public String getPrompt() {
        String claudeModelId = "anthropic.claude-3-5-sonnet-20240620-v1:0";
        String prompt = "Hello, how are you?";

        String payload = new JSONObject()
                        .put("prompt", prompt)
                        .put("max_tokens", 100)
                        .put("modelId", claudeModelId)
                        .put("temperature", 0.5)
                        .put("stop_sequences", List.of("\n\nHuman"))
                        .toString();

        InvokeModelRequest request = InvokeModelRequest.builder()
                        .body(SdkBytes.fromUtf8String(payload))
                        .modelId(claudeModelId)
                        .contentType("applicetion/json")
                        .accept("application/json")
                        .build();
        
        InvokeModelResponse response = bedrockRuntimeClient.invokeModel(request);

        JSONObject responseBody = new JSONObject(response.body().asUtf8String());
        
        String generedText = responseBody.getString("comletion");

        return generedText;

    }

}
