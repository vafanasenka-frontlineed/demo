package com.allot.domain.asm.frontend.page.isp.account;

import com.allot.core.driver.selenide.Actions;
import com.allot.domain.asm.frontend.model.User;
import com.allot.domain.asm.frontend.page.Tab;
import com.allot.domain.asm.frontend.page.table.Table;
import com.codeborne.selenide.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.allot.core.driver.selenide.Actions.isElementDisplayed;
import static com.allot.domain.asm.frontend.page.isp.account.UsersTab.UsersColumn.*;
import static com.allot.domain.asm.frontend.page.isp.account.UsersTab.UsersTabPageElement.*;


public class UsersTab implements Tab {

    public enum UsersColumn {

        NAME,
        PROFILE,
        ;
    }

    public UserProfilePage clickNewButton() {
        Selenide.$(NEW_BUTTON.getLocator()).click();
        return Selenide.page(UserProfilePage.class);
    }

    public void deleteUser(User user) {
        Table usersTable = getUsersTable();
        Table.Cell cell = usersTable.getRow(NAME.toString(), user.getName())
                .getActionsCell();
        Actions.clickViaJs(cell.getDeleteButton());
    }

    public List<User> getUserList() {
        Table usersTable = getUsersTable();
        return usersTable.getRows()
                .stream()
                .map(row -> User.builder()
                        .name(usersTable.getCellText(row, NAME.toString()))
                        .profile(usersTable.getCellText(row, PROFILE.toString()))
                        .build()
                    ).collect(Collectors.toList());
    }

    @Override
    public boolean isActive() {
        return Selenide.$(TAB.getLocator()).isDisplayed();
    }

    private Table getUsersTable() {
        SelenideElement userTable = Selenide.$(USERS_TABLE.getLocator());
        if (!isElementDisplayed(userTable)) {
            throw new RuntimeException("Users table is not displayed");
        }
        return new Table(userTable);
    }

    public enum UsersTabPageElement {

        TAB(" #Users "),
        NEW_BUTTON(TAB.getLocator() + " button.btn-md "),
        USERS_TABLE(TAB.getLocator() + " .el-table "),
        ;

        private String locator;

        UsersTabPageElement(String locator) {
            this.locator = locator;
        }

        public String getLocator() {
            return locator;
        }

    }

}
