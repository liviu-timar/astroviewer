# Astroviewer
Small example app to showcase my Android dev skills.

Astroviewer allows you to view pictures that the [NASA Astronomy Picture of the Day](https://apod.nasa.gov/apod/) web service provides. *Each day a different image or photograph of our fascinating universe is featured, along with a brief explanation written by a professional astronomer*, as they say.

### Tech Highlights
- CLEAN Architecture
- MVVM
- Jetpack Compose
- Compose Navigation
- Retrofit
- Local persistence with Room
- Dependency Injection with Hilt
- Kotlin Flow
- Tests

- To do
    - Migrate Details to Flow :white_check_mark:
    - Improve Details UI state management :white_check_mark:
    - Visual feedback for button clicks (reconsider global disabling of ripple effect) :white_check_mark:
    - Simplify naming for various types :white_check_mark:
    - Let users mark pictures as favourite & save favourites locally :white_check_mark:
    - Create cool animation for "Mark as Favourite" icon
    - Use streams of data (from Room to UI) instead of one-shot requests where possible (observe data instead of requesting it)
    - Use common top and bottom bars in Scaffold. Find an elegant way to customize from each screen. WIP on common-app-bars branch. 
    - Fixes for various edge cases

### Screens
| List Screen                                          | Sort Dialog                                          | Details Screen                                          |
|------------------------------------------------------|------------------------------------------------------|---------------------------------------------------------|
| <img src="/screens/List%20Screen.png" width="260" /> | <img src="/screens/Sort%20Dialog.png" width="260" /> | <img src="/screens/Details%20Screen.png" width="260" /> |

... and more soon.


---
###### Thank you for your interest!
