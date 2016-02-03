package com.blackducksoftware.tools.appuseradjuster;

import java.util.ArrayList;
import java.util.List;

import com.blackducksoftware.tools.commonframework.core.exception.CommonFrameworkException;
import com.blackducksoftware.tools.connector.codecenter.common.ApplicationRolePojo;
import com.blackducksoftware.tools.connector.codecenter.user.CodeCenterUserPojo;
import com.blackducksoftware.tools.connector.codecenter.user.ICodeCenterUserManager;

public class MockCodeCenterUserManager implements ICodeCenterUserManager {
    private boolean simulateRequestedUsersAlreadyExisted;

    private List<String> createdUsers = new ArrayList<>();

    public MockCodeCenterUserManager(boolean simulateRequestedUsersAlreadyExisted) {
        this.simulateRequestedUsersAlreadyExisted = simulateRequestedUsersAlreadyExisted;
    }

    public void setSimulateRequestedUsersAlreadyExisted(boolean simulateRequestedUsersAlreadyExisted) {
        this.simulateRequestedUsersAlreadyExisted = simulateRequestedUsersAlreadyExisted;
    }

    public List<String> getCreatedUsers() {
        return createdUsers;
    }

    public void clearCreatedUsers() {
        createdUsers = new ArrayList<>();
    }

    @Override
    public String createUser(String username, String password, String firstName, String lastName, String email, boolean active) throws CommonFrameworkException {
        createdUsers.add(username);
        return "userId_" + username.replace(" ", "_");
    }

    @Override
    public CodeCenterUserPojo getUserById(String userId) throws CommonFrameworkException {
        // TODO Auto-generated function stub
        return null;
    }

    @Override
    public CodeCenterUserPojo getUserByName(String userName) throws CommonFrameworkException {

        if (simulateRequestedUsersAlreadyExisted) {
            CodeCenterUserPojo user = new CodeCenterUserPojo(userName.replace(" ", "_"), userName, "test", "user", "", true);
            return user;
        } else {
            throw new CommonFrameworkException("MockCodeCenterUserManager: pretending user does not exist.");
        }

    }

    @Override
    public void deleteUserById(String userId) throws CommonFrameworkException {
        // TODO Auto-generated function stub

    }

    @Override
    public void setUserActiveStatus(String userId, boolean active) throws CommonFrameworkException {
        // TODO Auto-generated function stub

    }

    @Override
    public List<ApplicationRolePojo> getApplicationRolesByUserName(String userName) throws CommonFrameworkException {
        List<ApplicationRolePojo> roles = new ArrayList<>();
        for (int appIndex = 0; appIndex < 2; appIndex++) {

            ApplicationRolePojo role = new ApplicationRolePojo("testAppId" + appIndex, appIndex + "000-Test App-PROD-CURRENT", "Unspecified",
                    "testUserId", userName, "testRoleId",
                    "Test Role");
            roles.add(role);
            System.out.println("Role: " + role);

        }

        return roles;
    }

}
