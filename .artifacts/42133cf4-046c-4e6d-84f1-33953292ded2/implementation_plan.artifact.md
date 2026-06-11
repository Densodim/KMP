# Исправление проблемы пустого экрана (Сетевые запросы и модели)

Этот план направлен на устранение причин, по которым новости не отображаются на экране: отсутствие разрешения на интернет и ошибки десериализации данных.

## Proposed Changes

### Android App

#### [MODIFY] [AndroidManifest.xml](file:///C:/Home/work/KMP/androidApp/src/main/AndroidManifest.xml)
- Добавить разрешение `<uses-permission android:name="android.permission.INTERNET" />` перед тегом `<application>`. Без этого Android блокирует сетевой трафик.

### Shared Module (Domain Models)

#### [MODIFY] [Source.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/domain/models/Source.kt)
- Сделать поля `id` и `name` опциональными (`String?`), так как NewsAPI может возвращать `null` для идентификатора источника.

#### [MODIFY] [NewsItem.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/domain/models/NewsItem.kt)
- Сделать поля `title`, `description` и `content` опциональными (`String?`). Это предотвратит падение парсера JSON, если API пришлет неполные данные (что часто случается с `description`).

### Android App (UI)

#### [MODIFY] [NewsListItemView.kt](file:///C:/Home/work/KMP/androidApp/src/main/kotlin/com/example/testkmpapp/presentation/news/NewsListItemView.kt)
- Обновить использование `item.title` и `item.description`, добавив `.orEmpty()`, так как теперь они могут быть `null`.

## Verification Plan

### Automated Tests
- Запуск `analyze_file` для проверки корректности типов после изменений.

### Manual Verification
- Запустить приложение на устройстве/эмуляторе.
- Убедиться, что список новостей загружается и отображается.
