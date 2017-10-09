# FarepilotTechnicalChallenge

This app has been tested on a Samsung Galaxy S7 Edge (API 24) and a Sony Xperia Z1 Compact (API 19).

Due to the request to use specifically formatted server calls without a provided endpoint. I have created a 2 RestClient classes. 1 that uses retrofit to keep the format specified in the challenge expecting the correct server responses, and another stubbed version that returns appropriate responses to allow the app to functioning as expected.

In the technical challenge it was requested to display the username and password on the profile screen. As passwords are considered sensitive data. I have decided that it would be unwise to display such information clearly visible on the screen.

After starting a new session using the username and password, the server is expected to return a userId and token. The app then stores the userId and uses this to know if a successful login attempt has been made. However, in a new session it is not possible to get a new token without persisting the username and password rather than just the userId.

The camera intent returns an image that is of a much lower resolution than the photo taken. I have worked on using a FileProvider to get a higher resolution image. But on the Sony Xperia, this makes the Camera app crash as it does not handle the ContentProvider Uri correctly.

# Additional Functionality

With more time there is extra functionality that I could add to the app:

* If a user chooses a new avatar image while there is no network connection. The image could be uploaded once a connection is re-established.
