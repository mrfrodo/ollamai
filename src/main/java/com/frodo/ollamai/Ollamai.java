package com.frodo.ollamai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Ollamai {

	public static void main(String[] args) {
		SpringApplication.run(Ollamai.class, args);
	}

	/*
	@Bean
	public ApplicationRunner agentRunner(ChatClient.Builder chatClientBuilder) {
		return args -> {
			System.out.println("\n🤖 Contacting local Ollama instance (Streaming Mode)...");

			try {
				System.out.println("\n--- 🌤️ AI AGENT WEATHER REPORT ---");

				// 1. Initiate a stream instead of a blocking call
				Flux<String> responseStream = chatClientBuilder.build()
						.prompt("What is the weather like in London? Predict based on typical late May conditions if you lack live data.")
						.stream()
						.content();

				// 2. Subscribe to the stream and print tokens instantly as they generate
				responseStream
						.doOnNext(token -> System.out.print(token)) // Prints words on the same line instantly
						.doOnComplete(() -> System.out.println("\n---------------------------------\n")) // Closes the box when done
						.blockLast(); // Keeps the runner block active until the stream finishes

			} catch (Exception e) {
				System.err.println("❌ Error connecting to Ollama: " + e.getMessage());
				System.err.println("💡 Make sure your model matches your application.properties string exactly!");
			}
		};
	} */


}
