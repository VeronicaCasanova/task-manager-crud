package com.taskmanager.task_manager_crud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.taskmanager.task_manager_crud.entity.Task;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)

class TaskManagerCrudApplicationTests {

	private final WebTestClient webTestClient;

	@Autowired
	public TaskManagerCrudApplicationTests(WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	@Test
	void testCreateTaskSuccess() {
		var task = new Task("task 1", "desc task 1", false, 1);

		webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(task.getNome())
				.jsonPath("$[0].descricao").isEqualTo(task.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(task.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(task.getPrioridade());
	}

	@Test
	void testCreateTaskFailure() {
		webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(
						new Task("", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testUpdateTaskSuccess() {
		var task = new Task(2L, "Estudar Spring Boot 3 - Atualizado", "Finalizar estudo sobre Spring Boot - Atualizado", true, 4);
		webTestClient
				.put()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$[0].nome").isEqualTo(task.getNome());
	}

	@Test
	void testDeleteTaskSuccess() {
		// Primeiro, certifique-se de que a task com ID 3L existe antes de tentar excluí-la
		var task = new Task(3L, "Estudar Java e Spring", "Estudo detalhado sobre Spring Boot", false, 5);

		webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk();

		// Agora, exclua a task com ID 3L
		webTestClient
				.delete()
				.uri("/tasks/{id}", 3L)
				.exchange()
				.expectStatus().isOk();
	}


	@Test
	void testDeleteTaskNotFound() {
		webTestClient
				.delete()
				.uri("/tasks/{id}", 999L)
				.exchange()
				.expectStatus().isNotFound();
	}

}