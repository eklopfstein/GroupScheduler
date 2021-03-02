package uc.edu.klopfsea.groupscheduler

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import uc.edu.klopfsea.groupscheduler.ui.main.MainViewModel
import java.time.LocalDateTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CreateGroupUnitTest {

    @get : Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var mvm: MainViewModel

    @Test
    fun createAccountSuccessfully() {
        givenUserHasCreatedAccount()
        whenUserCreateGroupNameDnDWithPicture()
        thenGroupWithDnDAndPictureIsCreated()
    }

    private fun givenUserHasCreatedAccount() {
        mvm = MainViewModel()
    }

    private fun whenUserCreateGroupNameDnDWithPicture() {
        var newGroup = UserGroupsDTO("DnD", true, dateTime = LocalDateTime.now())
    }


    private fun thenGroupWithDnDAndPictureIsCreated() {
        var groupCreated = false;
        mvm.userGroupsDTO.observeForever {
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach {
                if (it.groupName == "DnD" && it.groupPicture.equals(true)) {
                    groupCreated = true;
                }
            }
            assertTrue(groupCreated)
        }

    }

    @Test
    fun CreateBlandGroupSuccessfully() {
        givenUserHasCreatedAccount()
        whenCreateGroupNameBlandWithoutPicture()
        thenGroupWithBlandNameIsCreated()
    }

    private fun whenCreateGroupNameBlandWithoutPicture() {
        var newGroup = UserGroupsDTO("Bland Group", false, dateTime = LocalDateTime.now())

    }

    private fun thenGroupWithBlandNameIsCreated() {
        var groupCreated = false;
        mvm.userGroupsDTO.observeForever {
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach {
                if (it.groupName == "Bland Group" && it.groupPicture.equals(false)) {
                    groupCreated = true;
                }
            }
            assertTrue(groupCreated)
        }
    }

    @Test
    fun CreateGroupUnSuccessfully() {
        givenUserHasCreatedAccount()
        whenCreateGroupWithNothing()
        thenGroupCreationIsFailed()
    }

    private fun whenCreateGroupWithNothing() {
        var newGroup = UserGroupsDTO("", false, dateTime = LocalDateTime.now())
    }

    private fun thenGroupCreationIsFailed() {
        var groupCreated = false;
        mvm.userGroupsDTO.observeForever {
            assertNotNull(it)
            assertTrue(it.size > 0)
            it.forEach {
                if (it.groupName == "" && it.groupPicture.equals(false)) {
                    groupCreated = false;
                }
            }
        }
    }
}