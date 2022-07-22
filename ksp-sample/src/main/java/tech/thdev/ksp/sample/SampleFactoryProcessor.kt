package tech.thdev.ksp.sample

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.asClassName
import tech.thdev.ksp.sample.annotation.GenerateAnnotation
import java.io.OutputStreamWriter

class SampleFactoryProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
) : SymbolProcessor {

    private val findClassList = mutableListOf<KSClassDeclaration>()

    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation(GenerateAnnotation::class.asClassName().canonicalName)
            .filter { ksAnnotated ->
                logger.warn("findKSAnnotated ${ksAnnotated is KSClassDeclaration}}")
                ksAnnotated is KSClassDeclaration
            }
            .map { ksAnnotated ->
                ksAnnotated as KSClassDeclaration
            }
            .filter { ksClassDeclaration ->
                val targetClass = ksClassDeclaration.superTypes
                    .map {
                        it.resolve().declaration
                    }
                    .firstOrNull { superType -> superType.simpleName.asString() == "AppCompatActivity" } != null

                if (targetClass.not()) {
                    logger.error("No activity class!!! ${ksClassDeclaration.simpleName.asString()} Remove @GenerateAnnotation", ksClassDeclaration)
                }
                true
            }
            .forEach {
                findClassList.add(it)
                logger.warn("findKSAnnotated ${it.simpleName.asString()}")
//                it.getDeclaredFunctions().forEach {
//                    logger.warn("Function ${it.simpleName.asString()}")
//                }
//                it.getDeclaredProperties().forEach {
//                    logger.warn("Properties ${it.simpleName.asString()}")
//                }
            }

        return emptyList()
    }

    override fun finish() {
        super.finish()

        findClassList.forEach { findClass ->
            val newPackageName = "${findClass.packageName.asString()}.extensions"
            val newFileName = "${findClass.simpleName.asString()}Extension"
            val context = ClassName("android.content", "Context")

            val file = FileSpec.builder(newPackageName, newFileName)
            file.addImport(findClass.packageName.asString(), findClass.simpleName.asString())
//            file.addImport("android.content", "Intent")
            file.addFunction(
                FunSpec
                    .builder("show${findClass.simpleName.asString()}") // 함수 이름
                    .receiver(context) // Receiver
                    .addCode(
                        "    Intent(this, SampleActivity::class.java).run {\n" +
                                "        startActivity(this)\n" +
                                "    }"
                    )
                    .build()
            )
            val outputStream = codeGenerator.createNewFile(
                Dependencies.ALL_FILES,
                newPackageName,
                newFileName,
            )
            OutputStreamWriter(outputStream, "UTF-8").use { file.build().writeTo(it) }
        }

/*        findClassList.forEach { findClass ->
//            logger.warn("findClass.packageName.getShortName() ${findClass.packageName.getShortName()}")
//            logger.warn("findClass.packageName.asString() ${findClass.packageName.asString()}")
//            logger.warn("findClass.packageName.getQualifier() ${findClass.packageName.getQualifier()}")
//            logger.warn("findClass.simpleName.asString() ${findClass.simpleName.asString()}")
//            logger.warn("findClass.simpleName.getQualifier() ${findClass.simpleName.getQualifier()}")
//            logger.warn("findClass.simpleName.getShortName() ${findClass.simpleName.getShortName()}")
            val newPackageName = "${findClass.packageName.asString()}.extensions"
            val newFileName = "${findClass.simpleName.asString()}Extension"
            val context = ClassName("android.content", "Context")
            val intent = ClassName("android.content", "Intent")

            val file = FileSpec.builder(newPackageName, newFileName)
            file.addImport(findClass.packageName.asString(), findClass.simpleName.asString())
            file.addImport(intent.packageName, intent.simpleName)
            file.addFunction(
                FunSpec
                    .builder("show${findClass.simpleName.asString()}") // 함수 이름
                    .receiver(context) // Receiver
                    .addCode(
                        "    Intent(this, %L::class.java).run {\n" +
                                "        startActivity(this)\n" +
                                "    }",
                        findClass.simpleName.asString()
                    )
                    .build()
            )
            val outputStream = codeGenerator.createNewFile(
                Dependencies.ALL_FILES,
                newPackageName,
                newFileName,
            )
            OutputStreamWriter(outputStream, "UTF-8").use { file.build().writeTo(it) }
        }*/
    }
}