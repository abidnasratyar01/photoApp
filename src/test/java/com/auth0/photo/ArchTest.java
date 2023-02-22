package com.auth0.photo;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.auth0.photo");

        noClasses()
            .that()
            .resideInAnyPackage("com.auth0.photo.service..")
            .or()
            .resideInAnyPackage("com.auth0.photo.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.auth0.photo.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
