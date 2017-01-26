package javaSeedMain;

import java.lang.reflect.InvocationTargetException;

import javaSeed.constants.Const;
import javaSeed.driverSheet.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {

		int ErrorCodeStatus=0;
		
		
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
				// Piece to read JIRATCKeyFAILLIST Array list for any Failed TC, if any return the Exit Code 1
				if(Const.JIRATCKeyFAILLIST.size()>=1){
					ErrorCodeStatus=1;
				}
				// End of return the Exit Code 1 piece
			}
		}else{
			String EnvItr = EnvParam;
			TriggerJavaSeed.TriggerScenarios(EnvItr);
			// Piece to read JIRATCKeyFAILLIST Array list for any Failed TC, if any return the Exit Code 1
			if(Const.JIRATCKeyFAILLIST.size()>=1){
				ErrorCodeStatus=1;
			}
			// End of return the Exit Code 1 piece
		}
		
		// Piece to read JIRATCKeyFAILLIST Array list for any Failed TC, if any return the Exit Code 1
		if(Const.JIRATCKeyFAILLIST.size()>=1){
			ErrorCodeStatus=1;
		}
		
		System.exit(ErrorCodeStatus);
	}
}
