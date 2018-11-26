package org.robo.move.tabletop.rest;

import org.robo.move.apis.IRoboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tabletop")
public class RobotTableTopRest {

	@Autowired
	@Qualifier("tableTopService")
	private IRoboService tableTopService;	
	
	@PutMapping("/place")
	@ResponseBody
	public ResponseEntity<String> place(@RequestParam(name="x") int x, @RequestParam(name="y") int y, @RequestParam(name="face") String face) {
		boolean placed = tableTopService.place(x, y, face);
		
		HttpStatus status = HttpStatus.OK;
		if (! placed) {
			// Request accepted, but ignored, to avoid making robot fall off table
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<String>(status);
	}
	
	@PutMapping("/move")
	@ResponseBody
	public ResponseEntity<String> move() {
		boolean moved = tableTopService.move();
		
		HttpStatus status = HttpStatus.OK;
		if (! moved) {
			// Request accepted, but ignored, to avoid making robot fall off table
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<String>(status);
	}
	
	@PutMapping("/left")
	@ResponseBody
	public ResponseEntity<String> left() {
		boolean rotated = tableTopService.left();
		
		HttpStatus status = HttpStatus.OK;
		if (! rotated) {
			// Request accepted, but ignored, to avoid making robot fall off table
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<String>(status);
	}
	
	@PutMapping("/right")
	@ResponseBody
	public ResponseEntity<String> right() {
		boolean rotated = tableTopService.right();
		
		HttpStatus status = HttpStatus.OK;
		if (! rotated) {
			// Request accepted, but ignored, to avoid making robot fall off table
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<String>(status);
	}
	
	@GetMapping("/report")
	@ResponseBody
	public ResponseEntity<String> report() {
		String report = tableTopService.report();
		return new ResponseEntity<String>(report, HttpStatus.OK);
	}
}
