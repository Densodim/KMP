# Исправление NewsUseCase и подготовка моделей

Я исправил ошибки в `NewsUseCase` и добавил необходимую сериализацию для моделей данных.

## Что было сделано

### 1. Исправление NewsUseCase.kt
- Исправлена опечатка в названии переменной: `newsServise` → `newsService`.
- Исправлена ошибка типов: теперь результат сетевого запроса распаковывается через `.getOrThrow()`. Это позволяет вашему `BaseUseCase` корректно обрабатывать успех и ошибки через `runCatching`.

### 2. Добавление сериализации
Для того чтобы Ktor мог преобразовывать JSON от сервера в объекты Kotlin, я добавил аннотацию `@Serializable` в следующие файлы:
- [Source.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/domain/models/Source.kt)
- [NewsItem.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/domain/models/NewsItem.kt)
- [NewsItemsList.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/domain/models/NewsItemsList.kt)

> [!TIP]
> Мы оставили название `NewsItemsList`, как вы и просили, не переименовывая его в `NewsList`.

## Верификация
- Файл `NewsUseCase.kt` успешно прошел статический анализ, ошибок компиляции больше нет.
- Все модели данных теперь готовы к работе с сетевым клиентом.

render_diffs(file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/api/network/NewsUseCase.kt)
render_diffs(file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/domain/models/NewsItemsList.kt)
