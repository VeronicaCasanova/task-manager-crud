package com.taskmanager.task_manager_crud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.taskmanager.task_manager_crud.entity.Task;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TaskManagerCrudApplicationTests {

	private final WebTestClient webTestClient;

	@Autowired
	public TaskManagerCrudApplicationTests(WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	private Long createTask(Task task) {
		return webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(task)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Task.class)
				.returnResult()
				.getResponseBody()
				.getId();
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
				.jsonPath("$.nome").isEqualTo(task.getNome())
				.jsonPath("$.descricao").isEqualTo(task.getDescricao())
				.jsonPath("$.realizado").isEqualTo(task.isRealizado())
				.jsonPath("$.prioridade").isEqualTo(task.getPrioridade());
	}

	@Test
	void testCreateTaskFailure() {
		webTestClient
				.post()
				.uri("/tasks")
				.bodyValue(new Task("", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
	void testUpdateTaskSuccess() {
		// Cria a tarefa e captura seu ID
		var originalTask = new Task("Estudar Spring Boot", "Finalizar estudo sobre Spring Boot", false, 3);
		Long taskId = createTask(originalTask);

		// Atualiza a tarefa
		var updatedTask = new Task(taskId, "Estudar Spring Boot 3 - Atualizado", "Finalizar estudo sobre Spring Boot - Atualizado", true, 4);

		webTestClient
				.put()
				.uri("/tasks/{id}", taskId) // Certifique-se de usar o ID correto
				.bodyValue(updatedTask)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.nome").isEqualTo(updatedTask.getNome())
				.jsonPath("$.descricao").isEqualTo(updatedTask.getDescricao())
				.jsonPath("$.realizado").isEqualTo(updatedTask.isRealizado())
				.jsonPath("$.prioridade").isEqualTo(updatedTask.getPrioridade());
	}

	@Test
	void testDeleteTaskSuccess() {
		// Cria a tarefa e captura seu ID
		var task = new Task("Estudar Java e Spring", "Estudo detalhado sobre Spring Boot", false, 5);
		Long taskId = createTask(task);

		// Exclui a tarefa criada
		webTestClient
				.delete()
				.uri("/tasks/{id}", taskId)
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void testDeleteTaskNotFound() {
		// Tenta deletar um ID inexistente
		webTestClient
				.delete()
				.uri("/tasks/{id}", 999L)
				.exchange()
				.expectStatus().isNotFound();
	}
}