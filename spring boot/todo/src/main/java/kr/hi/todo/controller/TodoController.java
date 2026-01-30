package kr.hi.todo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jdi.connect.Connector.BooleanArgument;

import kr.hi.todo.model.vo.TodoVO;
import kr.hi.todo.service.TodoService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/todos")
public class TodoController {
	
	private final TodoService todoService;
	
	public TodoController(TodoService todoService){
		this.todoService = todoService;
	}
	
	@GetMapping("")
	public ResponseEntity<List<TodoVO>> getTodos(){
		List<TodoVO> list = todoService.getTodos();
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
		
	}
	
	@PostMapping("")
	public ResponseEntity<Boolean> postTodos(
			@RequestBody TodoVO todo){
		boolean result = todoService.insertTodo(todo);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{num}")
	public ResponseEntity<Boolean> deleteTodos(
		@PathVariable("num") int num){
		boolean result = todoService.deleteTodo(num);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/{num}")
	public ResponseEntity<Boolean> putTodos(
		@PathVariable("num") int num,
		@RequestBody TodoVO todo){
		boolean res = todoService.updateTodo(todo);
		return ResponseEntity.ok(res);	}
	
	
	
	
}
