package com.developersancho.rortycompose.domain.usecase.location

import com.developersancho.rortycompose.data.repository.character.CharacterRepository
import com.developersancho.rortycompose.data.repository.location.LocationRepository
import com.developersancho.testutils.MockkUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetLocationDetailTest : MockkUnitTest() {
    @RelaxedMockK
    lateinit var charRepo: CharacterRepository

    @RelaxedMockK
    lateinit var locRepo: LocationRepository

    @SpyK
    @InjectMockKs
    private lateinit var getLocationDetail: GetLocationDetail

    @Test
    fun verifyExecute() = runTest {
        // Arrange (Given)
        val detailId = -1

        // Act (When)
        val params = GetLocationDetail.Params(detailId = detailId)
        getLocationDetail.invoke(params)

        // Assert (Then)
        coVerify { getLocationDetail.invoke(any()) }
    }
}