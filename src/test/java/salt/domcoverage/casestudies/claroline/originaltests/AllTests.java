package salt.domcoverage.casestudies.claroline.originaltests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import salt.domcoverage.casestudies.clarolinetest.AddCategory_pass;
import salt.domcoverage.casestudies.clarolinetest.AddNewCategory;
import salt.domcoverage.casestudies.clarolinetest.AddPhone;
import salt.domcoverage.casestudies.clarolinetest.Add_course;
import salt.domcoverage.casestudies.clarolinetest.DeletingCategory;
import salt.domcoverage.casestudies.clarolinetest.EditTextZone;
import salt.domcoverage.casestudies.clarolinetest.Exercise;
import salt.domcoverage.casestudies.clarolinetest.Login_logout;
import salt.domcoverage.casestudies.clarolinetest.Manage;
import salt.domcoverage.casestudies.clarolinetest.N;
import salt.domcoverage.casestudies.clarolinetest.Support;
import salt.domcoverage.casestudies.clarolinetest.Tc3;
import salt.domcoverage.casestudies.clarolinetest.add_user;
import salt.domcoverage.casestudies.clarolinetest.announcement;
import salt.domcoverage.casestudies.clarolinetest.assign;
import salt.domcoverage.casestudies.clarolinetest.course_category_edit;
import salt.domcoverage.casestudies.clarolinetest.course_desc;
import salt.domcoverage.casestudies.clarolinetest.s;
import salt.domcoverage.casestudies.clarolinetest.send_message;
import salt.domcoverage.casestudies.clarolinetest.user_list;
import salt.domcoverage.casestudies.clarolinetest.view_profile;
import salt.domcoverage.casestudies.clarolinetest.wiki;

@RunWith(Suite.class)
@SuiteClasses({ Add_course.class, add_user.class, AddCategory_pass.class, AddNewCategory.class, AddPhone.class, announcement.class, assign.class, course_category_edit.class, course_desc.class, DeletingCategory.class, EditTextZone.class, Exercise.class, Login_logout.class, Manage.class, N.class, s.class, send_message.class, Support.class, Tc3.class, user_list.class, view_profile.class, wiki.class })
public class AllTests {

}
