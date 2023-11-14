package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class Direction {
	int i;
	team.rocket.Enums.Direction direction;
	boolean[] booleans = new boolean[4];


	@Given("{string} is passed into directionFromString")
	public void isPassedIntoDirectionFromString(String string) {
		direction = team.rocket.Enums.Direction.directionFromString(string);
	}

	@Then("the returned direction should be left")
	public void theReturnedDirectionShouldBeLeft() {
		Assertions.assertEquals(team.rocket.Enums.Direction.LEFT, direction);
	}

	@Then("the returned direction should be right")
	public void theReturnedDirectionShouldBeRight() {
		Assertions.assertEquals(team.rocket.Enums.Direction.RIGHT, direction);
	}

	@Then("the returned direction should be up")
	public void theReturnedDirectionShouldBeUp() {
		Assertions.assertEquals(team.rocket.Enums.Direction.UP, direction);
	}

	@Then("the returned direction should be down")
	public void theReturnedDirectionShouldBeDown() {
		Assertions.assertEquals(team.rocket.Enums.Direction.DOWN, direction);
	}

	@Then("directionFromInt should throw illegalArgumentException when five is passed into it")
	public void directionfromintShouldThrowIllegalArgumentExceptionWhenFiveIsPassedIntoIt() {
		Assertions.assertThrows(IllegalArgumentException.class, () ->{
			team.rocket.Enums.Direction.directionFromInt(5);
		});
	}

	@Then("directionFromString should throw illegalArgumentException when a non-direction is passed into it")
	public void directionfromstringShouldThrowIllegalArgumentExceptionWhenANonDirectionIsPassedIntoIt() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			team.rocket.Enums.Direction.directionFromString("hello");
		});
	}

	@Then("randomDirectionFromBooleanArray should throw IllegalArgumentException when a too long boolean array is passed in")
	public void randomdirectionfrombooleanarrayShouldThrowIllegalArgumentExceptionWhenATooLongBooleanArrayIsPassedIn() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			team.rocket.Enums.Direction.randomDirectionFromBooleanArray(new boolean[3]);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			team.rocket.Enums.Direction.randomDirectionFromBooleanArray(new boolean[5]);
		});
	}

	@Then("randomDirectionFromBooleanArray returns null when passed the array")
	public void randomdirectionfrombooleanarrayReturnsNullWhenPassedTheArray() {
		Assertions.assertNull(team.rocket.Enums.Direction.randomDirectionFromBooleanArray(booleans));
	}

	@Then("randomDirectionFromBooleanArray returns UP")
	public void randomdirectionfrombooleanarrayReturnsUP() {
		Assertions.assertEquals(team.rocket.Enums.Direction.UP, team.rocket.Enums.Direction.randomDirectionFromBooleanArray(booleans));
	}

	@Then("randomDirectionFromBooleanArray returns LEFT")
	public void randomdirectionfrombooleanarrayReturnsLEFT() {
		Assertions.assertEquals(team.rocket.Enums.Direction.LEFT, team.rocket.Enums.Direction.randomDirectionFromBooleanArray(booleans));
	}

	@Given("The {} is saved.")
	public void theIsSaved(String integer) {
		i = Integer.parseInt(integer);
	}

	@When("{} is passed into directionFromInt")
	public void isPassedIntoDirectionFromInt(String integer) {
		direction = team.rocket.Enums.Direction.directionFromInt(i);
	}

	@Then("the return direction from directionFromInt is {}")
	public void theReturnDirectionFromDirectionFromIntIs(String returnDirection) {
		if(returnDirection.equalsIgnoreCase("left")) Assertions.assertEquals(team.rocket.Enums.Direction.LEFT, direction);
		else if(returnDirection.equalsIgnoreCase("right")) Assertions.assertEquals(team.rocket.Enums.Direction.RIGHT, direction);
		else if(returnDirection.equalsIgnoreCase("up")) Assertions.assertEquals(team.rocket.Enums.Direction.UP, direction);
		else if (returnDirection.equalsIgnoreCase("down")) Assertions.assertEquals(team.rocket.Enums.Direction.DOWN, direction);
	}

	@Given("a boolean array with values \\({int} , {int} , {int} , {int}) is made")
	public void aBooleanArrayWithValuesIsMade(int first, int second, int third, int fourth) {
		booleans[0] = first != 0;
		booleans[1] = second != 0;
		booleans[2] = third != 0;
		booleans[3] = fourth != 0;
	}
}
