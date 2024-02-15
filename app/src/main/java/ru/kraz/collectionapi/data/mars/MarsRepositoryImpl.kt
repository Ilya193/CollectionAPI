package ru.kraz.collectionapi.data.mars

import retrofit2.HttpException
import ru.kraz.collectionapi.domain.ErrorType
import ru.kraz.collectionapi.domain.ImageDomain
import ru.kraz.collectionapi.domain.MarsRepository
import ru.kraz.collectionapi.domain.ResultFDS
import java.lang.Exception
import java.net.UnknownHostException

class MarsRepositoryImpl(
    private val service: MarsService
) : MarsRepository {
    override suspend fun fetchImages(): ResultFDS<List<ImageDomain>> {
        return try {
            val images = service.fetchImages()
            ResultFDS.Success(images.map { it.toImageData().toImageDomain() })
        } catch (e: UnknownHostException) {
            ResultFDS.Error(ErrorType.NO_CONNECTION)
        } catch (e: HttpException) {
            ResultFDS.Error(ErrorType.SERVICE_UNAVAILABLE)
        } catch (e: Exception) {
            ResultFDS.Error(ErrorType.GENERIC_ERROR)
        }
    }
}