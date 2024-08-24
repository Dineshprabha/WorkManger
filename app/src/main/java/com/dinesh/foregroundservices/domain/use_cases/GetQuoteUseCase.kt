package com.dinesh.foregroundservices.domain.use_cases

import com.dinesh.foregroundservices.domain.repository.QuoteRepository
import javax.inject.Inject

class GetQuoteUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {

    operator fun invoke() = quoteRepository.getQuote()
}