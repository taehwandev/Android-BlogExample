package tech.thdev.ksp.sample

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class SampleProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
//        return SampleFactoryProcessorLogger(
//            codeGenerator = environment.codeGenerator,
//            logger = environment.logger,
//        )
        return SampleFactoryProcessor(
            codeGenerator = environment.codeGenerator,
            logger = environment.logger,
        )
    }
}