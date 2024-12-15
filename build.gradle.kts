import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.CoverageUnit
import kotlinx.kover.gradle.plugin.dsl.GroupingEntityType

plugins {
    id("org.jetbrains.kotlinx.kover") version "0.9.0"
    kotlin("jvm") version "2.0.21"
}

group = "com.rvigo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitVersion by extra { "5.11.3" }

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

kover {
    reports {
        group = GroupingEntityType.CLASS

        filters {
            excludes {
                classes(
                    "**\$Companion",
                    "**\$DefaultImpls",
                    "**\$special**"
                )
            }
        }
        verify {
            rule {
                bound {
                    coverageUnits = CoverageUnit.BRANCH
                    minValue = 100
                    aggregationForGroup = AggregationType.COVERED_PERCENTAGE
                }
            }
        }
    }
}