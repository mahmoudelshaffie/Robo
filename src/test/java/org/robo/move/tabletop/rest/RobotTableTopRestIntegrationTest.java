package org.robo.move.tabletop.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robo.move.RoboMoveApiApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = RoboMoveApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RobotTableTopRestIntegrationTest {

	private String BASE_URL = "api/tabletop";
	
	private String NORTH = "NORTH";
	private String SOUTH = "SOUTH";
	private String WEST = "WEST";
	private String EAST = "EAST";
	
	private String REPORT = "/report";
	private String MOVE = "/move";
	private String PLACE = "/place";
	private String LEFT = "/left";
	private String RIGHT = "/right";
	
	// Table Surface UNITS
	private final int MAX_UNITS = 5;
	private final int MAX_X = MAX_UNITS - 1;
	private final int MAX_Y = MAX_UNITS - 1;
	private final int ZERO_X = 0;
	private final int ZERO_Y = 0;
	private final int X_EQ_2 = 2;
	private final int Y_EQ_3 = 3;
	
	private final int OK = HttpStatus.OK.value();
	private final int ACCEPTED = HttpStatus.ACCEPTED.value();
	private final int BAD_REQUEST = HttpStatus.BAD_REQUEST.value();

	@LocalServerPort
	private int port;
	
	private void assertReport(String EXPECTED_REPORT) {
		given().port(port).basePath(BASE_URL).get(REPORT).then().assertThat()
		.statusCode(OK).and().body(equalTo(EXPECTED_REPORT));
	}
	
	private void place(int x, int y, String direction, int status) {
		given().port(port).basePath(BASE_URL)
		.queryParam("x", x).queryParam("y", y).queryParam("face", direction)
		.put(PLACE).then().assertThat()
		.statusCode(status);
	}
	
	private void move(int status) {
		given().port(port).basePath(BASE_URL).put(MOVE)
		.then().assertThat().statusCode(status);
	}
	
	private void left(int status) {
		given().port(port).basePath(BASE_URL).put(LEFT).then().assertThat()
		.statusCode(status);
	}
	
	private void right(int status) {
		given().port(port).basePath(BASE_URL).put(RIGHT).then().assertThat()
		.statusCode(status);
	}
	

	@Test
	public void testReportWithoutPlacementShouldResponseWithMissingRobotBodySuccessfully() {
		String EXPECTED_REPORT = "ROBOT MISSING";
		assertReport(EXPECTED_REPORT);
	}
	
	@Test
	public void testMoveWithoutPlacementShouldResponseWithAcceptedButNotCompletedStatus() {
		move(ACCEPTED);
	}
	
	@Test
	public void testRotateLeftWithoutPlacementShouldResponseWithAcceptedButNotCompletedStatus() {
		left(ACCEPTED);
	}
	
	@Test
	public void testRotateRighttWithoutPlacementShouldResponseWithAcceptedButNotCompletedStatus() {
		right(ACCEPTED);
	}
	
	@Test
	public void testPlaceWithValidPosition2XAnd3YAndNorthDirectionShouldBePlacedSuccessfully() {
		place(X_EQ_2, Y_EQ_3, NORTH, OK);
		
		String EXPECTED_REPORT = "2,3,NORTH";
		assertReport(EXPECTED_REPORT);
	}
	
	@Test
	public void testPlaceWithZeroXandYShouldBePlacedSuccessfully() {
		place(ZERO_X, ZERO_Y, NORTH, OK);
		
		String EXPECTED_REPORT = "0,0,NORTH";
		assertReport(EXPECTED_REPORT);
	}
	
	@Test
	public void testPlaceTwiceWithTwoValidLocationsShouldBePlacedSuccessfully() {
		place(X_EQ_2, Y_EQ_3, NORTH, OK);
		String EXPECTED_FIRST_REPORT = "2,3,NORTH";
		assertReport(EXPECTED_FIRST_REPORT);
		
		place(ZERO_X, ZERO_Y, SOUTH, OK);
		String EXPECTED_SECOND_REPORT = "0,0,SOUTH";
		assertReport(EXPECTED_SECOND_REPORT);
	}
	
	@Test
	public void testPlaceWithValidPositionButInvalidDirectionShouldResponseWithBadRequest() {
		String INVALID_DIRECTION = "SUTH";
		place(X_EQ_2, Y_EQ_3, INVALID_DIRECTION, BAD_REQUEST);
	}
	
	@Test
	public void testPlaceWithNegativeXShouldResponseWithAcceptedButNotCompletedStatus() {
		int NEGATIVE_X = -2;
		place(NEGATIVE_X, Y_EQ_3, NORTH, ACCEPTED);
	}
	
	@Test
	public void testPlaceWithNegativeYShouldResponseWithAcceptedButNotCompletedStatus() {
		int NEGATIVE_Y = -3;
		place(X_EQ_2, NEGATIVE_Y, NORTH, ACCEPTED);
	}
	
	@Test
	public void testPlaceWithNegativeXAndYShouldResponseWithAcceptedButNotCompletedStatus() {
		int NEGATIVE_X = -2;
		int NEGATIVE_Y = -3;
		place(NEGATIVE_X, NEGATIVE_Y, NORTH, ACCEPTED);
	}
	
	@Test
	public void testPlaceWithExceededXShouldResponseWithAcceptedButNotCompletedStatus() {
		int exceededX = MAX_UNITS + 1;
		place(exceededX, Y_EQ_3, NORTH, ACCEPTED);
	}
	
	@Test
	public void testPlaceWithExceededYShouldResponseWithAcceptedButNotCompletedStatus() {
		int exceededY = MAX_UNITS + 2;
		place(X_EQ_2, exceededY, NORTH, ACCEPTED);
	}
	
	@Test
	public void testPlaceWithExceededXandYShouldResponseWithAcceptedButNotCompletedStatus() {
		int exceededX = MAX_UNITS + 1;
		int exceededY = MAX_UNITS + 2;
		place(exceededX, exceededY, NORTH, ACCEPTED);
	}
	
	@Test
	public void testPlaceWithXandYEqualsMaxUnitsShouldResponseWithAcceptedButNotCompletedStatus() {
		int exceededX = MAX_UNITS;
		int exceededY = MAX_UNITS;
		place(exceededX, exceededY, NORTH, ACCEPTED);
	}
	
	@Test
	public void testMoveAfterPlacementShouldBeMovedSuccessfully() {
		place(X_EQ_2, Y_EQ_3, NORTH, OK);
		String firstPlaceReport = "2,3,NORTH";
		assertReport(firstPlaceReport);
		
		given().port(port).basePath(BASE_URL).put(MOVE)
		.then().assertThat().statusCode(OK);
		
		String EXPECTED_REPORT = "2,4,NORTH";
		assertReport(EXPECTED_REPORT);
	}
	
	@Test
	public void testMoveTwiceSecondOneIsInvalidShouldNotBeMovedAndResponseWithAcceptedButNotCompletedStatus() {
		place(X_EQ_2, Y_EQ_3, NORTH, OK);
		String firstPlaceReport = "2,3,NORTH";
		assertReport(firstPlaceReport);
		
		move(OK);
		
		String FIRST_MOVE_REPORT = "2,4,NORTH";
		assertReport(FIRST_MOVE_REPORT);
		
		move(ACCEPTED);
		
		assertReport(FIRST_MOVE_REPORT);
	}
	
	@Test
	public void testMoveInvalidTowardsNorthShouldNotBeMovedAndResponseWithAcceptedButNotCompletedStatus() {
		place(X_EQ_2, MAX_Y, NORTH, OK);
		String firstPlaceReport = "2,4,NORTH";
		assertReport(firstPlaceReport);
		
		move(ACCEPTED);
		
		assertReport(firstPlaceReport);
	}
	
	@Test
	public void testMoveInvalidTowardsSouthShouldNotBeMovedAndResponseWithAcceptedButNotCompletedStatus() {
		place(ZERO_X, ZERO_Y, SOUTH, OK);
		String firstPlaceReport = "0,0,SOUTH";
		assertReport(firstPlaceReport);
		
		move(ACCEPTED);
		
		assertReport(firstPlaceReport);
	}
	
	@Test
	public void testMoveInvalidTowardsWESTShouldNotBeMovedAndResponseWithAcceptedButNotCompletedStatus() {
		place(ZERO_X, Y_EQ_3, WEST, OK);
		String firstPlaceReport = "0,3,WEST";
		assertReport(firstPlaceReport);
		
		move(ACCEPTED);
		
		assertReport(firstPlaceReport);
	}
	
	@Test
	public void testMoveInvalidTowardsEASTShouldNotBeMovedAndResponseWithAcceptedButNotCompletedStatus() {
		place(MAX_X, Y_EQ_3, EAST, OK);
		String firstPlaceReport = "4,3,EAST";
		assertReport(firstPlaceReport);
		
		move(ACCEPTED);
		
		assertReport(firstPlaceReport);
	}
	
	@Test
	public void testRotateLeftFromNorthAfterPlacementShouldRotateSuccessfullyTowardsWEST() {
		place(X_EQ_2, Y_EQ_3, NORTH, OK);
		String firstPlaceReport = "2,3,NORTH";
		assertReport(firstPlaceReport);
		
		left(OK);
		
		String afterRorationReport = "2,3,WEST";
		assertReport(afterRorationReport);
	}
	
	@Test
	public void testRotateLeftFromWESTAfterPlacementShouldRotateSuccessfullyTowardsSOUTH() {
		place(X_EQ_2, Y_EQ_3, WEST, OK);
		String firstPlaceReport = "2,3,WEST";
		assertReport(firstPlaceReport);
		
		left(OK);
		
		String afterRorationReport = "2,3,SOUTH";
		assertReport(afterRorationReport);
	}
	
	@Test
	public void testRotateLeftFromSouthAfterPlacementShouldRotateSuccessfullyTowardsEast() {
		place(X_EQ_2, Y_EQ_3, SOUTH, OK);
		String firstPlaceReport = "2,3,SOUTH";
		assertReport(firstPlaceReport);
		
		left(OK);
		
		String afterRorationReport = "2,3,EAST";
		assertReport(afterRorationReport);
	}
	
	@Test
	public void testRotateLeftFromEastAfterPlacementShouldRotateSuccessfullyTowardsNorth() {
		place(X_EQ_2, Y_EQ_3, EAST, OK);
		String firstPlaceReport = "2,3,EAST";
		assertReport(firstPlaceReport);
		
		left(OK);
		
		String afterRorationReport = "2,3,NORTH";
		assertReport(afterRorationReport);
	}
	
	@Test
	public void testRotateRightFromNorthAfterPlacementShouldRotateSuccessfullyTowardsEast() {
		place(X_EQ_2, Y_EQ_3, NORTH, OK);
		String firstPlaceReport = "2,3,NORTH";
		assertReport(firstPlaceReport);
		
		right(OK);
		
		String afterRorationReport = "2,3,EAST";
		assertReport(afterRorationReport);
	}
	
	@Test
	public void testRotateLeftFromWESTAfterPlacementShouldRotateSuccessfullyTowardsNorth() {
		place(X_EQ_2, Y_EQ_3, WEST, OK);
		String firstPlaceReport = "2,3,WEST";
		assertReport(firstPlaceReport);
		
		right(OK);
		
		String afterRorationReport = "2,3,NORTH";
		assertReport(afterRorationReport);
	}
	
	@Test
	public void testRotateLeftFromSouthAfterPlacementShouldRotateSuccessfullyTowardsWest() {
		place(X_EQ_2, Y_EQ_3, SOUTH, OK);
		String firstPlaceReport = "2,3,SOUTH";
		assertReport(firstPlaceReport);
		
		right(OK);
		
		String afterRorationReport = "2,3,WEST";
		assertReport(afterRorationReport);
	}
	
	@Test
	public void testRotateRighFromEastAfterPlacementShouldRotateSuccessfullyTowardsSouth() {
		place(X_EQ_2, Y_EQ_3, EAST, OK);
		String firstPlaceReport = "2,3,EAST";
		assertReport(firstPlaceReport);
		
		right(OK);
		
		String afterRorationReport = "2,3,SOUTH";
		assertReport(afterRorationReport);
	}
	
}
