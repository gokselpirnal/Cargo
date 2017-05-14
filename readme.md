# Cargo

Cargo is a simple sender/receiver communication for Android. 

You should look the [EventBus](https://github.com/greenrobot/EventBus)  

### What does it do

Communication between fragments and activities



## Example


```java
// paste to Activity or Fragment

@Address
public void clickedToButton(ButtonClicked pkg){
    Log.d("Cargo","Yaay! New package: " + pkg.getClass().getSimpleName());
}

@Address
public void clickedToImage(ImageClicked pkg){
    Log.d("Cargo","Yaay! New package: " + pkg.getClass().getSimpleName());
}

@Override
public void onStart() {
    super.onStart();
    Cargo.openTheBranch(this);
}

@Override
public void onStop() {
    super.onStop();
    Cargo.closeTheBranch(this);
}
```



```java
// paste to sender

Cargo.deliver(new ButtonClicked());
Cargo.deliver(new ImageClicked());
```