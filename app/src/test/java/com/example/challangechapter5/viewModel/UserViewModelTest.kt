package com.example.challangechapter5.viewModel

import com.example.challangechapter5.model.GetResponseUserItem
import com.example.challangechapter5.service.RestfulUser
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class UserViewModelTest {
    lateinit var service : RestfulUser
    @Before
    fun setUp() {
        service = mockk()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getUserTest(){
        val respAllUser = mockk<Call<List<GetResponseUserItem>>>()

        every {
                service.getAllUser()
        }returns respAllUser

        val result = service.getAllUser()
        verify {
                service.getAllUser()
            Assert.assertEquals(result,respAllUser)
        }
    }
    @Test
    fun testUser(){
        val respAllUser = mockk<Call<List<GetResponseUserItem>>>()
        every {
            service.getAllUser()
        }returns respAllUser

        val result = service.getAllUser()

        verify {
            service.getAllUser()
        }
        Assert.assertEquals(result,respAllUser)
    }
}

