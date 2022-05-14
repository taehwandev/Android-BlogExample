package tech.thdev.ksp.sample

import com.google.devtools.ksp.getDeclaredFunctions
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import tech.thdev.ksp.sample.annotation.GenerateAnnotation

class SampleFactoryProcessorLogger(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation(GenerateAnnotation::class.java.canonicalName)
            .filter { ksAnnotated ->
                ksAnnotated is KSClassDeclaration
            }
            .map { ksAnnotated ->
                ksAnnotated as KSClassDeclaration
            }
            .forEach {
                logger.warn("findKSAnnotated ${it.simpleName.asString()}")
                it.getDeclaredFunctions().forEach {
                    logger.warn("Function ${it.simpleName.asString()}")
                }
                it.getDeclaredProperties().forEach {
                    logger.warn("Properties ${it.simpleName.asString()}")
                }
            }

        return emptyList()
    }
}