package javaSeedMain;

import java.lang.reflect.InvocationTargetException;

import javaSeed.driverSheet.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {

/*		String EnvItr = args[0];
		TriggerJavaSeed.TriggerScenarios(EnvItr);*/		
				
		String EnvParam = args[0].toString();
		int intEnvItrStart,intEnvItrEnd;		
		
		if(EnvParam.contains("-")){
			String[] arrEnvParam = EnvParam.split("-");
			intEnvItrStart = Integer.parseInt(arrEnvParam[0]);
			intEnvItrEnd = Integer.parseInt(arrEnvParam[1]);
			for(int i=intEnvItrStart;i<=intEnvItrEnd;i++){
				TriggerJavaSeed.TriggerScenarios(String.valueOf(i));
			}
		}else{
			String EnvItr = EnvParam;
			TriggerJavaSeed.TriggerScenarios(EnvItr);
		}
	}
}
