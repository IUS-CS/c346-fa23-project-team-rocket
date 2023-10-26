package team.rocket.Handlers.Terminal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the TimeStepsPerDay flag
 * Ex: --steps_per_day 12
 * @since 0.5.0
 * @version 0.5.0
 */
public class TimeStepsPerDayFlagHandler extends FlagHandler{
	@Override
	public void handleRequest(TerminalFlagRequest tFRequest){
		//Need to identify strings that follow the pattern "steps_per_day 6"
		//Split at "--" to identify each flag relevant to this request Handler
		String[] flags = tFRequest.getTerminalCommand().split("--");
		Pattern pattern = Pattern.compile("steps_per_day [0-9]{1,10}");
		Matcher matcher;
		for(String flag: flags){
			matcher = pattern.matcher(flag);
			boolean matchFound = matcher.find();
			if(matchFound){
				//Take the flag split on the space and grab the second half then split that half at " and grab the first section
				char a = '"'; // "
				int stepsPerDay = Integer.parseInt(flag.split(" ")[1].split(String.valueOf(a))[0]);
				tFRequest.setStepsPerDay(stepsPerDay);
			}
		}
	}
}
