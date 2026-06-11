# Рефакторинг на UseCase и исправление UI

Внедрение `NewsUseCase` в архитектуру приложения и исправление ошибок компиляции в `NewsListScreen.kt` для соответствия подходу из книги.

## User Review Required

> [!IMPORTANT]
> Я изменю конструкторы `NewsService` и `NewsUseCase`, чтобы они поддерживали внедрение зависимостей (Dependency Injection) вручную через объект `DI`. Это сделает код более тестируемым и гибким.

## Proposed Changes

### Shared Module: Сетевой слой и DI

#### [MODIFY] [NewsService.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/api/network/NewsService.kt)
- Изменить конструктор, чтобы он принимал `NetworkClient`.
- Убрать внутреннее создание `NetworkClient`.

#### [MODIFY] [NewsUseCase.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/api/network/NewsUseCase.kt)
- Изменить конструктор, чтобы он принимал `NewsService`.
- Убрать внутреннее создание `NewsService`.

#### [MODIFY] [DI.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/di/DI.kt)
- Исправить создание `NewsService`.
- Добавить метод `getNewsUseCase()`, который собирает цепочку: `NetworkClient` -> `NewsService` -> `NewsUseCase`.

---

### Android App: Presentation Layer

#### [MODIFY] [NewsViewModel.kt](file:///C:/Home/work/KMP/androidApp/src/main/kotlin/com/example/testkmpapp/presentation/news/NewsViewModel.kt)
- Заменить `DI.getNewsService()` на `DI.getNewsUseCase()`.
- Обновить `fetchData()`, чтобы он вызывал `useCase.invoke(Unit)`.
- Учесть, что `useCase.invoke` возвращает `Result<NewsItemsList?>`.

#### [MODIFY] [NewsListScreen.kt](file:///C:/Home/work/KMP/androidApp/src/main/kotlin/com/example/testkmpapp/presentation/news/NewsListScreen.kt)
- Исправить тип переменной `news` при сборе состояния.
- Убрать несуществующий аргумент `onClick` при вызове `NewsListView`.

## Verification Plan

### Automated Tests
- Анализ всех измененных файлов с помощью `analyze_file` для подтверждения отсутствия синтаксических ошибок.
- Проверка связей между компонентами.

### Manual Verification
- После исправлений проект должен успешно собираться в Android Studio.
