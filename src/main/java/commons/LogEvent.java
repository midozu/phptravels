package commons;

import org.apache.log4j.Logger;

public class LogEvent {
	public static Logger Log = Logger.getLogger(LogEvent.class.getName());

	public void startTestCase(String TestCaseName) {
		Log.info("==========" + TestCaseName + "==========");
	}

	public void endTestCase() {
		Log.info(".................-E---N---D-.................\n");
	}

	public void info(String message) {
		Log.info(message);
	}

	public void warn(String message) {
		Log.warn(message);
	}

	public void error(String message) {
		Log.error(message);
	}

	public void fatal(String message) {
		Log.fatal(message);
	}

	public void debug(String message) {
		Log.debug(message);
	}

}
