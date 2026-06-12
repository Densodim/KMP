# Внедрение Koin DI в проект KMP

Этот план описывает завершение настройки Koin Dependency Injection для обеспечения работы как на Android, так и на iOS, следуя подходу из вашей книги.

## User Review Required

> [!IMPORTANT]
> В проекте уже присутствует **Hilt** (`@HiltAndroidApp` в `TestKMPApplication`). Если вы решили полностью перейти на **Koin** (как в книге), я рекомендую в дальнейшем убрать Hilt, чтобы не усложнять проект двумя разными системами DI. В рамках этого плана я добавлю инициализацию Koin рядом с Hilt.

## Proposed Changes

### Shared Module (commonMain)

#### [MODIFY] [Koin.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/di/Koin.kt)
- Добавить `networkModule` для регистрации сетевых настроек.
- Создать `viewModelModule` для регистрации `NewsViewModels`.
- Исправить синтаксис `startKoin`: использовать `modules(allModules)` вместо некорректного `module { listOf(...) }`.
- Добавить поддержку `KoinAppDeclaration` для специфичных настроек платформ (например, передача `Context` на Android).

#### [MODIFY] [KoinDI.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/di/KoinDI.kt)
- Сделать класс более удобным для iOS: добавить инъекцию `NewsUseCase`.
- Убрать `val service2: NewsService = get()`, так как прямой вызов `get()` в конструкторе может привести к падению, если Koin еще не запущен.

#### [MODIFY] [NewsViewModels.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/domain/models/NewsViewModels.kt)
- Убрать ручное создание `useCase` через старый `DI` объект.
- Добавить конструктор для инъекции `NewsUseCase` или использовать `KoinComponent`.

### Android App

#### [MODIFY] [TestKMPApplication.kt](file:///C:/Home/work/KMP/androidApp/src/main/kotlin/com/example/testkmpapp/TestKMPApplication.kt)
- Вызвать `initKoin` в методе `onCreate`.

## Verification Plan

### Automated Tests
- Проверка синтаксиса всех файлов через `analyze_file`.

### Manual Verification
- Сборка проекта.
- Проверка логов (если добавить `print` в инициализацию модулей) для подтверждения успешного запуска Koin.
