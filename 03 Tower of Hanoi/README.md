<div align="center">
	<h1>Tower of Hanoi</h1>
</div>

![towerofhanoi_banner](https://user-images.githubusercontent.com/41904540/194917363-c366e1f9-67a5-45c6-8e20-7fc0d87372c8.png)

## 🎲 The Tower of Hanoi

The **[Tower of Hanoi](https://en.wikipedia.org/wiki/Tower_of_Hanoi)** is a **mathematical game** or **puzzle**
consisting of **three towers** and a number of **disks of various diameters**,
the objective of the puzzle is to move the **entire stack** to **another tower**.

> **NOTE:** The **[Tower of Hanoi](https://en.wikipedia.org/wiki/Tower_of_Hanoi) must obey** the **following rules**
>
> | Rule | Details                                                                                                                                     |
> | :--: | ------------------------------------------------------------------------------------------------------------------------------------------- |
> | 1    | Only **one disk** may be **moved** at a time.                                                                                               |
> | 2    | Each move consists of taking the **upper disk** from one of the towers and placing it on top of **another tower** or on an **empty tower**. |
> | 3    | **No disk** may be **placed on top** of a disk that is **smaller** than it.                                                                 |
>
> ⭐ *The term **`legal move`** used throughout this assignment refers to **disk movements** that **obey the rules** mentioned above*.

This assignment consists only resources from **[Unit 2](https://apstudents.collegeboard.org/courses/ap-computer-science-a)** and **[Unit 3](https://apstudents.collegeboard.org/courses/ap-computer-science-a)**
of the **[Advanced Placement Computer Science A](https://apstudents.collegeboard.org/courses/ap-computer-science-a)** course;
although there might be solutions that come with more experienced features,
you should be able to complete this with **just knowledge from the two units**.

## 🧰 Objects

Here's **all the objects** you would be using throughout this assignment,
you could **view them** 👀 by **clicking** on them.

(Remember to click as *gently* as possible to avoid having them frightened. ❤️)

<details>
	<summary><b>HanoiTower</b> (Click to Expand)</summary>

> **HanoiTower:** Game object that includes all the tower and disks
>
> ```java
> /**
>  * Request a disk movement from a tower to another, throws an exception if unattainable
>  * @param disk_from The original (source) tower ID of the disk
>  * @param disk_to The new (destination) tower ID of the disk
>  */
> public void move_disk(int disk_from, int disk_to) {}
> /**
>  * Gets the amount of disks in a tower
>  * @param tower_id The ID of the tower
>  * @return The amount of disks in a tower
>  */
> public int get_height(int tower_id) {}
> /**
>  * Gets the disk located at the top of a tower
>  * @param tower_id The ID of the tower
>  * @return The disk located at the top of a tower
>  */
> public HanoiDisk get_top(int tower_id) {}
> /**
>  * Gets the total amount of disks
>  * @return The total amount of disks
>  */
> public int get_disks() {}
> /**
>  * Gets the total amount of successful disk moves
>  * @return The total amount of successful disk moves
>  */
> public int get_moves() {}
> ```
</details>


<details>
	<summary><b>HanoiDisk</b> (Click to Expand)</summary>

> **HanoiDisk:** Disk that could be transfered between towers
>
> ```java
> /**
>  * Gets the ID of the disk
>  * @return The ID of the disk
>  */
> public byte get_id() {}
> ```
</details>

<details>
	<summary><b>Graphics</b> (Click to Expand)</summary>

> **Graphics:** Utilities for graphical programming in terminals
>
> | Color                    | Color                     | Color                   |
> | ------------------------ | ------------------------- | ----------------------- |
> | Graphics.**RED_DARK**    | Graphics.**AQUA_DARK**    | Graphics.**WHITE**      |
> | Graphics.**RED_LIGHT**   | Graphics.**AQUA_LIGHT**   | Graphics.**GRAY_DARK**  |
> | Graphics.**GOLD**        | Graphics.**BLUE_DARK**    | Graphics.**GRAY_LIGHT** |
> | Graphics.**YELLOW**      | Graphics.**BLUE_LIGHT**   | Graphics.**BLACK**      |
> | Graphics.**GREEN_DARK**  | Graphics.**PURPLE_DARK**  | Graphics.**BOLD**       |
> | Graphics.**GREEN_LIGHT** | Graphics.**PURPLE_LIGHT** | Graphics.**RESET**      |
>
> ```java
> /**
>  * Applies foreground text color to the following string
>  * @param red The red value of the foreground RGB color
>  * @param green The green value of the foreground RGB color
>  * @param blue The blue value of the foreground RGB color
>  * @return The chainable Graphics object
>  */
> public Graphics foreground(byte red, byte green, byte blue) {}
> /**
>  * Applies background text color to the following string
>  * @param red The red value of the background RGB color
>  * @param green The green value of the background RGB color
>  * @param blue The blue value of the background RGB color
>  * @return The chainable Graphics object
>  */
> public Graphics background(byte red, byte green, byte blue) {}
> /**
>  * Applies bold styling to the following string
>  * @return The chainable Graphics object
>  */
> public Graphics bold() {}
> /**
>  * Reset the styling of the following string
>  * @return The chainable Graphics object
>  */
> public Graphics reset() {}
> ```
</details>

## ✔️ Q1: Check if Disks are Legally Movable

There's a **common misunderstanding** of the effects of games on peoples' **physical and mental health**,
that the majority believe games could lead to a **children's toxicness** and further ~~*commit crimes*~~ insult their parents 👍.
As a result, we the *Association of Friendly Games for Everyone*, aims to **address** the fact that **they're wrong**.
What affects peoples negatively is their **greed of playing unfairly** and **taking advantages** over others;
while games that are **played fairly** would be **100% friendly** to everyone, including new-borns. 🧒

> **Effects of Games on Evoluted Monkis** 🐒🧑‍🤝‍🧑
>
> | Monki Type | Age        | Unfair Games                       | Fair Games                         |
> | :--------: | :--------: | ---------------------------------- | ---------------------------------- |
> | Evoluting  | < 2 Months | -99.99% Physical and Mental Health | +99.98% Physical and Mental Health |
> | Devoluting | > 2 Months | -99.97% Physical and Mental Health | +99.99% Physical and Mental Health |
>
> Accurate statistics provided with ❤️ by the *Association of Friendly Games for Everyone*

As you could see from the **statistics above**, **unfair games** are **extremely harmful**.
To prevent such thing from happening ever again and ruin our world, we have to **teach everyone** how to **obey rules correctly** in games.
You are the **chosen one** ~~*to be tortured*~~ so please help us get this resolved ASAP (as soon as possible)
by **creating a program** that determines whether peoples are **playing the game correctly**.

> **GOAL:** Determine whether a move between towers is legal, return true for legal movement or false otherwise.
> ```java
> /**
>  * Check whether a disk could be legally moved from a tower to another (Unit 2 & Unit 3)
>  * @param board The Hanoi Tower object
>  * @param disk_from The original (source) tower ID of the disk
>  * @param disk_to The new (destination) tower ID of the disk
>  * @return Whether a disk could be legally moved from a tower to another
>  */
> public static boolean disk_movable(HanoiTower board, int disk_from, int disk_to) {}
> ```
> | Origin Tower | Destination Tower | Top Disks                | Movable |
> | :----------: | :---------------: | ------------------------ | :-----: |
> | 0            | 1                 | [**`1`**, **`2`**, 3]    | ✔️       |
> | 0            | 1                 | [**`1`**, **`null`**, 3] | ✔️       |
> | 1            | 0                 | [**`1`**, **`2`**, 3]    | ❌       |
> | 1            | 0                 | [**`1`**, **`null`**, 3] | ❌       |
>
> **TIP:** Check out the definition of the term **`legal move`** mentioned under game rules.

## 👫 Q2: Perform Legal Move between Towers

Nice! 👍 Seems like you had resolved the issue of peoples playing games unfairly, yet it sure isn't good enough for us.
While you were away, we observed that the **world could be even better** by simply **avoid having miscommunications**. 🤐
After an in-deep discussion with the professionals in our association,
we proudly concluded that the best solution is to **simplify our use of language**.

Rather then mentioning *`"move the disk from tower A to tower B"`* 🙄,
we could instead say *`"A & B"`* to perform a legal disk move between the towers.
Since you had helped us before we truly believe in you,
please help us make a program to perform the task.

> **GOAL:** Perform a **legal move** 💱 between the towers with IDs of **`tower_a`** and **`tower_b`**.
> ```java
>  /**
>   * Legally moves a disk between two towers (Unit 2 & Unit 3)
>   * @param board The Hanoi Tower object
>   * @param tower_a The first tower, could be either the source or destination of the disk
>   * @param tower_b The second tower, could be either the source or destination of the disk
>   */
>  public static void disk_movement_legal(HanoiTower board, int tower_a, int tower_b) {}
> ```
> | Tower A | Tower B | Top Disks (Before)       | Top Disks (After)        |
> | :-----: | :-----: | ------------------------ | ------------------------ |
> | 0       | 1       | [**`null`**, **`1`**, 2] | [**`1`**, **`?`**, 2]    |
> | 1       | 2       | [null, **`1`**, **`2`**] | [null, **`?`**, **`1`**] |
> | 2       | 0       | [**`null`**, 1, **`2`**] | [**`2`**, 1, **`?`**]    |
>
> **TIP:** There's maximum one solution considered as a **`legal move`**,
> which is either moving the **smaller disk** above the **greater disk**
> or transferring the **disk** to another **tower without a disk**.

## 🦾 Q3: Automate Tower Solution

*(Note: Incomplete Description)*

> **GOAL:** Automate the solution for the **Tower of Hanoi**.
> ```java
>  /**
>   * Solves the Tower of Hanoi, one disk movement per execution (Unit 2 & Unit 3)
>   * @param board The Hanoi Tower object
>   */
>  public static void hanoi_tower_solution(HanoiTower board) {}
> ```
> | Moves | Task | Top Disks (Before)          | Top Disks (After)        |
> | :---: | :--: | --------------------------- | ------------------------ |
> | 0     | 1    | [**`1`**, null, **`null`**] | [**`2`**, null, **`1`**] |
> | 1     | 2    | [**`2`**, **`null`**, 1]    | [**`3`**, **`2`**, 1]    |
> | 2     | 3    | [3, **`2`**, **`1`**]       | [3, **`1`**, **`null`**] |
> | 3     | 1    | [**`3`**, 1, **`null`**]    | [**`4`**, 1, **`3`**]    |
> | 4     | 2    | [**`4`**, **`1`**, 3]       | [**`1`**, **`2`**, 3]    |
> | 5     | 3    | [1, **`2`**, **`3`**]       | [1, **`null`**, **`2`**] |
> 
> **TIP:** You could keep track with the current **moves of disks** with the
> **`HanoiTower.get_moves()`** method binded to the **board object**.

## 🎮 Q4: Player Controller (Open-Ended)

![towerofhanoi_openended](https://user-images.githubusercontent.com/41904540/199811719-8a59c291-8202-4b60-a2ba-8af34c406edb.png)