package javaSeed.utils.jiraConnection;

import java.util.Base64;
import java.util.List;
import javaSeed.constants.Const;

public class JIRAUpdate {
	public static final String JIRA_UPDATEFLAG = Const.ENVIRONMENT_EXEC_ARRAY[2];
	public static final String JIRA_SERVER = Const.ENVIRONMENT_EXEC_ARRAY[8];
	public static final String JIRA_USERNAME = Const.ENVIRONMENT_EXEC_ARRAY[9];
	public static final String JIRA_B64PASSWORD = Const.ENVIRONMENT_EXEC_ARRAY[10];
	public static final String JIRA_PROJECTKEY = Const.ENVIRONMENT_EXEC_ARRAY[11];
	public static final String JIRA_PROJ_VERSIONNAME = Const.ENVIRONMENT_EXEC_ARRAY[12];
	public static final String JIRA_PROJ_CYCLENAME = Const.ENVIRONMENT_EXEC_ARRAY[13];
	public static final String JIRA_REPORTUPLOAD_TCKEY = Const.ENVIRONMENT_EXEC_ARRAY[14];

	public static Boolean ConnectJiraUpdateTCStatus(ZephyrConfigModel ZCModel, List<String> passList,List<String> failList) {
		
		Boolean StatusUpdateStatus = null;
		try{
				StatusUpdateStatus = TestCaseUtil.executeTests(ZCModel, passList, failList);
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
	return StatusUpdateStatus;
	}
	
	public static Boolean UploadTestExecutionReport(ZephyrConfigModel ZCModel, String ReportPath, String IssueKey) {

		Boolean AttachmentStatus = null;
		try{
		
			AttachmentStatus = TestCaseUtil.AddAttachmentJIRAExecution(ZCModel, ReportPath, IssueKey);
		
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
	return AttachmentStatus;
	}
	
	
	public static ZephyrConfigModel MakeConnectionGetTCsFromCycle() {
		
		ZephyrConfigModel ZCModel = new ZephyrConfigModel();
		
		try{
			
		// Declarations AND Input Parameters
		String VersionID=null,projectID=null,CycleID=null;
		
		// ZephyrInstance Object instantiation 
		ZephyrInstance zephyrServer = new ZephyrInstance();		
		zephyrServer.setServerAddress(JIRA_SERVER);
		zephyrServer.setUsername(JIRA_USERNAME);
		zephyrServer.setPassword(new String(Base64.getDecoder().decode(JIRA_B64PASSWORD)));
		
		// RestClient Object instantiation 
		//RestClient oRestClient = new RestClient(zephyrServer);
		RestClient oRestClient = new RestClient(JIRA_SERVER, JIRA_USERNAME, new String(Base64.getDecoder().decode(JIRA_B64PASSWORD)));
		projectID = Project.getProjectIdByKey(JIRA_PROJECTKEY, oRestClient);
		if(projectID==null){
			return null;
		} else {
			VersionID = Project.getProjectVersionNameID(JIRA_PROJECTKEY, JIRA_PROJ_VERSIONNAME, oRestClient);
			CycleID=Cycle.getCyclesIDByName(VersionID,projectID, JIRA_PROJ_CYCLENAME, oRestClient);
			// ZephyrConfigModel Object instantiation 
			
			ZCModel.setZephyrProjectId(Long.parseLong(projectID));
			ZCModel.setVersionId(Long.parseLong(VersionID));		
			ZCModel.setCycleId(Long.parseLong(CycleID));
			ZCModel.setCycleName(JIRA_PROJ_CYCLENAME);
			ZCModel.setRestClient(oRestClient);
			ZCModel.setTCKeyID(TestCaseUtil.fetchIssueKeyIdSummaryFromCycle(ZCModel));
		}
		
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return ZCModel;
	}
}