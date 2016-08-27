package db;

import db.tables.CombinedQueriesImplementation;
import db.tables.Implementation.ImplementationDAO;
import db.tables.TableConstants;
import db.tables.client.ClientDAO;
import db.tables.company.CompanyDAO;
import db.tables.edition.EditionDAO;
import db.tables.os.OsDAO;
import db.tables.os_edition.OsEditionDAO;
import db.tables.program.ProgramDAO;
import db.tables.program_os.ProgramOsDAO;
import db.tables.programming.ProgrammingDAO;
import db.tables.programming_language.ProgLanguageDAO;
import db.tables.programming_methodology.ProgrammingMethodologyDAO;
import db.tables.project.ProjectDAO;
import db.tables.project_requirement.ProjectReqDAO;
import db.tables.requirement.ReqDAO;
import db.tables.software_engineer.SoftwareEngineerDAO;
import db.tables.team.TeamDAO;
import db.tables.version.VersionDAO;
import entities.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dima on 21.04.2016.
 */
public class DBOperations {
    public static String NO_ERROR_MESSAGE = "NO_ERROR";

    private ProgramDAO programDAO;
    private ProjectDAO projectDAO;
    private ClientDAO clientDAO;
    private CompanyDAO companyDAO;
    private EditionDAO editionDAO;
    private ImplementationDAO implementationDAO;
    private OsDAO osDAO;
    private OsEditionDAO osEditionDAO;
    private ProgramOsDAO programOsDAO;
    private ProgrammingDAO programmingDAO;
    private ProgLanguageDAO progLanguageDAO;
    private ProgrammingMethodologyDAO programmingMethodologyDAO;
    private ProjectReqDAO projectReqDAO;
    private ReqDAO reqDAO;
    private SoftwareEngineerDAO softwareEngineerDAO;
    private TeamDAO teamDAO;
    private VersionDAO versionDAO;

    private CombinedQueriesImplementation combinedQueriesImplementation;


    public DBOperations(){
        programDAO = new ProgramDAO();
        projectDAO = new ProjectDAO();
        clientDAO = new ClientDAO();
        companyDAO = new CompanyDAO();
        editionDAO = new EditionDAO();
        implementationDAO = new ImplementationDAO();
        osDAO = new OsDAO();
        osEditionDAO = new OsEditionDAO();
        programOsDAO = new ProgramOsDAO();
        programmingDAO = new ProgrammingDAO();
        progLanguageDAO = new ProgLanguageDAO();
        programmingMethodologyDAO = new ProgrammingMethodologyDAO();
        projectReqDAO = new ProjectReqDAO();
        reqDAO = new ReqDAO();
        softwareEngineerDAO = new SoftwareEngineerDAO();
        teamDAO = new TeamDAO();
        versionDAO = new VersionDAO();

        combinedQueriesImplementation = new CombinedQueriesImplementation();
    }

    public Object[][] getPrograms(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(programDAO.getList(), TableConstants.PROGRAM_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getClient(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(clientDAO.getList(), TableConstants.CLIENT_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getCompanies(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(companyDAO.getList(), TableConstants.COMPANY_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getEditions(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(editionDAO.getList(), TableConstants.EDITION_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }


    public Object[][] getImpl(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(implementationDAO.getList(), TableConstants.IMPLEMENTATION_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }


    public Object[][] getOs(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(osDAO.getList(), TableConstants.OS_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }


    public Object[][] getOsEdition(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(osEditionDAO.getList(), TableConstants.OS_EDITION_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }


    public Object[][] getProgramOs(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(programOsDAO.getList(), TableConstants.PROGRAM_OS_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getProgramming(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(programmingDAO.getList(), TableConstants.PROGRAMMING_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getProgLang(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(progLanguageDAO.getList(), TableConstants.PROG_LANG_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getProgMethodology(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(programmingMethodologyDAO.getList(), TableConstants.PROGRAMMING_METH_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }
    public Object[][] getProject(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(projectDAO.getList(), TableConstants.PROJECT_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getProjectReq(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(projectReqDAO.getList(), TableConstants.PROJECT_REQ_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getReq(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(reqDAO.getList(), TableConstants.REQ_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getSoftwareEngineer(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(softwareEngineerDAO.getList(), TableConstants.SOFTWARE_ENGINEER_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getTeam(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(teamDAO.getList(), TableConstants.TEAM_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getVersion(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(versionDAO.getList(), TableConstants.VERSION_NUMBER_OF_COLUMNS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public String addProgram(Program program){
        try {
            programDAO.addProgram(program);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateProgram(Program program){
        try {
            programDAO.updateProgram(program);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteProgram(String programName){
        try {
            programDAO.deleteProgram(programName);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addClient(Client client){
        try {
            clientDAO.addClient(client);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateClient(Client client){
        try {
            clientDAO.updateClient(client);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteClient(String clientName){
        try {
            clientDAO.deleteClient(clientName);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addCompany(Company company){
        try {
            companyDAO.addCompany(company);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateCompany(Company company){
        try {
            companyDAO.updateCompany(company);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteCompany(int companyId){
        try {
            companyDAO.deleteCompany(companyId);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addEdition(Edition edition){
        try {
            editionDAO.addEditon(edition);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateEdition(Edition edition){
        try {
            editionDAO.updateEdition(edition);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteEdition(String edition){
        try {
            editionDAO.deleteEdition(edition);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addImpl(Implementation implementation){
        try {
            implementationDAO.addImpl(implementation);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateImpl(Implementation implementation){
        try {
            implementationDAO.updateImpl(implementation);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteImpl(String program, String lang){
        try {
            implementationDAO.deleteImpl(program,lang);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addOs(Os os){
        try {
            osDAO.addOs(os);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateOs(Os os){
        try {
            osDAO.updateOs(os);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteOs(String os){
        try {
            osDAO.deleteOs(os);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addOsEdition(OsEdition osEdition){
        try {
            osEditionDAO.addOsEdition(osEdition);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateOsEdition(OsEdition osEdition){
        try {
            osEditionDAO.updateOsEdition(osEdition);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteOsEdition(String os, String osEdition){
        try {
            osEditionDAO.deleteOsEdition(os, osEdition);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addProgramOs(ProgramOs programOs){
        try {
            programOsDAO.addProgramOs(programOs);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateProgramOs(ProgramOs programOs){
        try {
            programOsDAO.updateProgramOs(programOs);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteProgramOs(String program, String os){
        try {
            programOsDAO.deleteProgramOs(program, os);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addProgramming(Programming programming){
        try {
            programmingDAO.addProgramming(programming);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateProgramming(Programming programming){
        try {
            programmingDAO.updateProgramming(programming);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteProgramming(String softwareEng, String program){
        try {
            programmingDAO.deleteProgramming(softwareEng, program);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addProgLang(ProgLang progLang){
        try {
            progLanguageDAO.addProgLang(progLang);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateProgLang(ProgLang progLang){
        try {
            progLanguageDAO.updateProgLang(progLang);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteProgLang(String name){
        try {
            progLanguageDAO.deleteProgLangs(name);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addProgMethodology(ProgrammingMethodology programmingMethodology){
        try {
            programmingMethodologyDAO.addProgMethodology(programmingMethodology);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateProgMethodology(ProgrammingMethodology programmingMethodology){
        try {
            programmingMethodologyDAO.updateProgMethodology(programmingMethodology);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteProgMethodology(String name){
        try {
            programmingMethodologyDAO.deleteProgMethodology(name);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addProject(Project project){
        try {
            projectDAO.addProject(project);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateProject(Project project){
        try {
            projectDAO.updateProject(project);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteProject(String projectName){
        try {
            projectDAO.deleteProject(projectName);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addProjectReq(ProjectReq projectReq){
        try {
            projectReqDAO.addProjectReq(projectReq);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateProjectReq(ProjectReq projectReq){
        try {
            projectReqDAO.updateProjectReq(projectReq);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteProjectReq(String project, int req){
        try {
            projectReqDAO.deleteProjectReq(project, req);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addReq(Req req){
        try {
            reqDAO.addReq(req);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateReq(Req req){
        try {
            reqDAO.updateReq(req);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteReq(int req){
        try {
            reqDAO.deleteReq(req);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addSoftwareEnginner(SoftwareEngineer softwareEngineer){
        try {
            softwareEngineerDAO.addSe(softwareEngineer);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateSoftwareEngineer(SoftwareEngineer softwareEngineer){
        try {
            softwareEngineerDAO.updateSe(softwareEngineer);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteSoftwareEngineer(String initials){
        try {
            softwareEngineerDAO.deleteSe(initials);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addTeam(Team team){
        try {
            teamDAO.addTeam(team);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateTeam(Team team){
        try {
            teamDAO.updateTeam(team);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteTeam(String teamName){
        try {
            teamDAO.deleteTeam(teamName);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String addVersion(Version version){
        try {
            versionDAO.addVersion(version);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String updateVersion(Version version){
        try {
            versionDAO.updateVersion(version);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public String deleteVersion(Date releaseDate){
        try {
            versionDAO.deleteVersion(releaseDate);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    public ArrayList<String> getProjectNameList(){
        ArrayList<String> data = null;
        try {
            data = projectDAO.getNamesList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> getProgramNameList(){
        ArrayList<String> data = null;
        try {
            data = programDAO.getNamesList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> getEditionNameList(){
        ArrayList<String> data = null;
        try {
            data = editionDAO.getNamesList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> getOsNameList(){
        ArrayList<String> data = null;
        try {
            data = osDAO.getNamesList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> getCompanyIdList(){
        ArrayList<String> data = null;
        try {
            data = companyDAO.getIdList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> getSEInitialsList(){
        ArrayList<String> data = null;
        try {
            data = softwareEngineerDAO.getInitialsList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> getTeamNameList(){
        ArrayList<String> data = null;
        try {
            data = teamDAO.getNamesList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> getReqIdList(){
        ArrayList<String> data = null;
        try {
            data = reqDAO.getIdList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<String> getProgLangNameList(){
        ArrayList<String> data = null;
        try {
            data = progLanguageDAO.getNamesList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public int getPercentOfProgramThatUseTwoOrMoreOs(){
        Integer percent = null;
        try {
            percent = programDAO.getPercentOfProgramThatUseTwoOrMoreOs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return percent;
    }

    public Object[][] getOsFrequencyOfUse(){
        Object[][] data = null;
        try {
            data = DBUtils.listToObjectArray(programOsDAO.getOsFrequencyOfUse(),2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Object[][] getPopularityOfProgramLanguagesInProgram(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(progLanguageDAO.getPopularityOfProgramLanguagesInProgram(),4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getSuccessfulProjectMethodology(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(projectDAO.getSuccessfulProjectMethodology(),3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getTopRequirementAtWrongTime(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(projectReqDAO.getTopRequirementAtWrongTime(),1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getTopRequirementOnTime(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(projectReqDAO.getTopRequirementOnTime(),1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public int getNumberRows(String tableName){
        int numberOfRows = -1;
        try {
            if (tableName.equals("Version")) {
                numberOfRows = clientDAO.getNumberOfRows();
            }
            else if (tableName.equals("Edition")) {
                numberOfRows = editionDAO.getNumberOfRows();
            }
            else if (tableName.equals("OS edition")) {
                numberOfRows = osEditionDAO.getNumberOfRows();
            }
            else if (tableName.equals("Client")) {
                numberOfRows = clientDAO.getNumberOfRows();
            }
            else if (tableName.equals("Team")) {
                numberOfRows = teamDAO.getNumberOfRows();
            }
            else if (tableName.equals("Programming methodology")) {
                numberOfRows = programmingMethodologyDAO.getNumberOfRows();
            }
            else if (tableName.equals("OS")) {
                numberOfRows = osDAO.getNumberOfRows();
            }
            else if (tableName.equals("Program")) {
                numberOfRows = programDAO.getNumberOfRows();
            }
            else if (tableName.equals("Program-OS")) {
                numberOfRows = programOsDAO.getNumberOfRows();
            }
            else if (tableName.equals("Programming")) {
                numberOfRows = programmingDAO.getNumberOfRows();
            }
            else if (tableName.equals("Software Engineer")) {
                numberOfRows = softwareEngineerDAO.getNumberOfRows();
            }
            else if (tableName.equals("Project")) {
                numberOfRows = projectDAO.getNumberOfRows();
            }
            else if (tableName.equals("Project - Requirement")) {
                numberOfRows = projectReqDAO.getNumberOfRows();
            }
            else if (tableName.equals("Implementation")) {
                numberOfRows = implementationDAO.getNumberOfRows();
            }
            else if (tableName.equals("Requirement")) {
                numberOfRows = reqDAO.getNumberOfRows();
            }
            else if (tableName.equals("Programming Language")) {
                numberOfRows = progLanguageDAO.getNumberOfRows();
            }
            else if (tableName.equals("Company")) {
                numberOfRows = companyDAO.getNumberOfRows();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numberOfRows;
    }

    public String updateCompanyPrivilege(String companyPrivilege, int companyId){
        try {
            companyDAO.updateCompanyPrivilege(companyPrivilege, companyId);
            return NO_ERROR_MESSAGE;
        } catch (SQLException e) {
            return (e.getMessage());
        }
    }

    /*
    ***********************ADDITIONAL QUERIES********************************************************
     */

    public Object[][] getFinishedProjects(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation.getFinishedProjects(),5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getPercentOfPerformationEachRequirementInFinishedProjects(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation
                    .getPercentOfPerformationEachRequirementInFinishedProjects(),2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getProgramNamesAndWhatNumberOsTheyUse(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation.getProgramNamesAndWhatNumberOsTheyUse(),2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getWhatNumberOsUseProgram(String program){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation.getWhatNumberOsUseProgram(program),2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getPercentRatioNumberOfUsingOsInPrograms(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation.getPercentRatioNumberOfUsingOsInPrograms(),2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getProgramsOfProjectWithTheHighestBudget(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation.getProgramsOfProjectWithTheHighestBudget(),2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getProgramsOfProjectWithTheLowestBudget(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation.getProgramsOfProjectWithTheLowestBudget(),2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public String getProjectWithTheHighestBudget(){
        String projectWithHighestBudget = null;
        try {
            projectWithHighestBudget = combinedQueriesImplementation.getProjectWithTheHighestBudget();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectWithHighestBudget;
    }

    public String getProjectWithTheLowestBudget(){
        String projectWithLowestBudget = null;
        try {
            projectWithLowestBudget = combinedQueriesImplementation.getProjectWithTheLowestBudget();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectWithLowestBudget;
    }

    public Object[][] getWhatProgLanguagesOfProgram(String program){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation.getWhatProgLanguagesOfProgram(program),2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getWhatProgLanguagesUsedWithOsAsTheMostPopular(String program){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation
                    .getWhatProgLanguagesUsedWithOsAsTheMostPopular(program),1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getAtFinishedProjectsNumberOfRequirementsAtAllAndPerformed(){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation
                    .getAtFinishedProjectsNumberOfRequirementsAtAllAndPerformed(),3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getAtProjectNumberOfRequirementsAtAllAndPerformed(String project){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation
                    .getAtProjectNumberOfRequirementsAtAllAndPerformed(project),3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public Object[][] getFinishedProjectsWithPercentRatioOfRequirementsPerformed(int percent){
        Object[][] objectArray = null;
        try {
            objectArray = DBUtils.listToObjectArray(combinedQueriesImplementation
                    .getFinishedProjectsWithPercentRatioOfRequirementsPerformed(percent),3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectArray;
    }

    public CompanyDAO getCompanyDAO() {
        return companyDAO;
    }

    public static void main(String... args){
        DBOperations dbOperations = new DBOperations();
        DBConnection.init();
        dbOperations.getFinishedProjects();
        dbOperations.getPercentOfPerformationEachRequirementInFinishedProjects();
        dbOperations.getProgramNamesAndWhatNumberOsTheyUse();
        dbOperations.getWhatNumberOsUseProgram("");
        dbOperations.getPercentRatioNumberOfUsingOsInPrograms();
        dbOperations.getProgramsOfProjectWithTheHighestBudget();
        dbOperations.getProgramsOfProjectWithTheLowestBudget();
        dbOperations.getProjectWithTheHighestBudget();
        dbOperations.getProjectWithTheLowestBudget();
        dbOperations.getWhatProgLanguagesOfProgram("");
        dbOperations.getWhatProgLanguagesUsedWithOsAsTheMostPopular("");
        dbOperations.getAtFinishedProjectsNumberOfRequirementsAtAllAndPerformed();
        dbOperations.getAtProjectNumberOfRequirementsAtAllAndPerformed("");
        dbOperations.getFinishedProjectsWithPercentRatioOfRequirementsPerformed(50);


    }

}
