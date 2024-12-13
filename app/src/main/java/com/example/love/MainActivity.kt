package com.example.love

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.love.ui.theme.LoveTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoveTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "mainScreen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("mainScreen") { MainScreen(navController) }
                        composable("gameSelectionScreen") { GameSelectionScreen(navController) }
                        composable("gameScreen") { GameScreen(navController) }
                        composable("dailygameScreen") { DailyGameScreen(navController) }
                        composable("homegameScreen") { HomeGameScreen(navController)  }
                        composable("imageCategoryScreen") { ImageCategoryScreen(navController) } // 添加目标页面
                        composable("imageDescriptionScreen") { ImageDescriptionScreen(navController) }
                        composable("imageDailyScreen") { ImageDailyScreen(navController) }
                        composable("imageHomeScreen") { ImageHomeScreen(navController) }
                    }

                }

            }
        }
    }
}


@Composable
fun MainScreen(navController: NavController) {
    // 加载自定义字型
    val customFont = FontFamily(
        Font(R.font.fonttt)  // fonttt.ttf 在 res/font 目錄中
    )

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF004B97))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "翻翻有愛",
                    fontSize = 24.sp,
                    color = Color.White,
                    style = androidx.compose.ui.text.TextStyle(fontFamily = customFont)
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 這裡是圖片介紹按鈕，按下後會導航到圖片介紹頁面
//                RectangularButton(text = "圖片介紹",modifier = Modifier.fillMaxWidth(), height = 200.dp) {
//                    navController.navigate("imageDescriptionScreen")
//                }

                // 圖片介紹分類按鈕
                RectangularButton(text = "圖片介紹分類", modifier = Modifier.fillMaxWidth(), height = 200.dp) {
                    navController.navigate("imageCategoryScreen")
                }

                // 中间的间隔距离
                Spacer(modifier = Modifier.height(16.dp)) // 16.dp 是你想要的间距，可以调整

                // 配對遊戲按鈕
//                RectangularButton(text = "配對遊戲", height = 200.dp) {
//                    navController.navigate("gameScreen")
//                }

                // 這裡是選擇配對遊戲按鈕，按下後會導航到配對遊戲選擇頁面
                RectangularButton(text = "選擇配對遊戲",modifier = Modifier.fillMaxWidth(), height = 200.dp) {
                    navController.navigate("gameSelectionScreen") // 導航到選擇配對遊戲頁面
                }
            }
        }
    }
}

// 圖片介紹分類頁面
@Composable
fun ImageCategoryScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 圖片介紹按鈕
            RectangularButton(text = "圖片介紹", modifier = Modifier.fillMaxWidth(), height = 100.dp) {
                navController.navigate("imageDescriptionScreen")
            }

            // 日常用品圖片介紹按鈕
            RectangularButton(text = "日常用品圖片介紹", modifier = Modifier.fillMaxWidth(), height = 100.dp) {
                navController.navigate("ImageDailyScreen")
            }

            // 愛心家園元素介紹按鈕
            RectangularButton(text = "愛心家園元素介紹", modifier = Modifier.fillMaxWidth(), height = 100.dp) {
                navController.navigate("ImageHomeScreen")
            }
        }


        // 右下角的返回主页按钮
        BackToHomeButton(navController, modifier = Modifier.offset(y = 50.dp))
    }
}

// 圖片介紹頁面
@Composable
fun ImageDescriptionScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        // 使用 LazyColumn 显示可滚动的图片列表
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // 设置每张图片之间的间距
        ) {
            items(8) { index ->
                // 根据索引选择不同的图片
                ImageCard(index + 1)
            }
        }


        // 右下角的返回主页按钮
        BackToHomeButton(navController, modifier = Modifier.offset(y = 50.dp))
    }
}

@Composable
fun ImageCard(imageIndex: Int) {
    val imageRes = when (imageIndex) {
        1 -> R.drawable.interview1
        2 -> R.drawable.interview2
        3 -> R.drawable.interview3
        4 -> R.drawable.interview4
        5 -> R.drawable.interview5
        6 -> R.drawable.interview6
        7 -> R.drawable.interview7
        8 -> R.drawable.interview8
        else -> R.drawable.interview1 // 默认图片
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()  // 每张图片占满宽度
            .height(300.dp),  // 设置每张图片的高度
        contentAlignment = Alignment.Center
    ) {
        // 使用 Image 显示图片
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "圖片 $imageIndex",
            modifier = Modifier.fillMaxSize()
        )
    }
}

// 日常介紹頁面
@Composable
fun ImageDailyScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        // 使用 LazyColumn 显示可滚动的图片列表
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // 设置每张图片之间的间距
        ) {
            items(8) { index ->
                // 根据索引选择不同的图片
                ImageDailyCard(index + 1)
            }
        }


        // 右下角的返回主页按钮
        BackToHomeButton(navController, modifier = Modifier.offset(y = 50.dp))
    }
}

@Composable
fun ImageDailyCard(imageIndex: Int) {
    val imageRes = when (imageIndex) {
        1 -> R.drawable.interview17
        2 -> R.drawable.interview18
        3 -> R.drawable.interview19
        4 -> R.drawable.interview20
        5 -> R.drawable.interview21
        6 -> R.drawable.interview22
        7 -> R.drawable.interview23
        8 -> R.drawable.interview24
        else -> R.drawable.interview17 // 默认图片
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()  // 每张图片占满宽度
            .height(300.dp),  // 设置每张图片的高度
        contentAlignment = Alignment.Center
    ) {
        // 使用 Image 显示图片
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "圖片 $imageIndex",
            modifier = Modifier.fillMaxSize()
        )
    }
}

// 日常介紹頁面
@Composable
fun ImageHomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        // 使用 LazyColumn 显示可滚动的图片列表
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // 设置每张图片之间的间距
        ) {
            items(8) { index ->
                // 根据索引选择不同的图片
                ImageHomeCard(index + 1)
            }
        }


        // 右下角的返回主页按钮
        BackToHomeButton(navController, modifier = Modifier.offset(y = 50.dp))
    }
}

@Composable
fun ImageHomeCard(imageIndex: Int) {
    val imageRes = when (imageIndex) {
        1 -> R.drawable.interview9
        2 -> R.drawable.interview10
        3 -> R.drawable.interview11
        4 -> R.drawable.interview12
        5 -> R.drawable.interview13
        6 -> R.drawable.interview14
        7 -> R.drawable.interview15
        8 -> R.drawable.interview16
        else -> R.drawable.interview9 // 默认图片
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()  // 每张图片占满宽度
            .height(300.dp),  // 设置每张图片的高度
        contentAlignment = Alignment.Center
    ) {
        // 使用 Image 显示图片
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "圖片 $imageIndex",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun BackToHomeButton(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),  // 给按钮添加一些外边距
        contentAlignment = Alignment.BottomEnd  // 定位到右下角
    ) {
        Button(
            onClick = { navController.navigate("mainScreen") },  // 导航到主页面
            modifier = Modifier.padding(16.dp)  // 给按钮添加一些内边距
        ) {
            Text(text = "返回主頁")  // 按钮的文本
        }
    }
}

@Composable
fun GameSelectionScreen(navController: NavController) {
    // 加載自定義字型
    val customFont = FontFamily(
        Font(R.font.fonttt)  // fonttt.ttf 在 res/font 目錄中
    )

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF004B97))
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "選擇配對遊戲",
                    fontSize = 24.sp,
                    color = Color.White,
                    style = androidx.compose.ui.text.TextStyle(fontFamily = customFont)
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 创建一个Row，使用horizontalScroll来实现滑动效果
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()), // 开启水平滚动
                    horizontalArrangement = Arrangement.spacedBy(16.dp) // 按钮之间的间距
                ) {

                    // 職業配對遊戲按鈕的圖片與按鈕
                    Box {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.work), // 替換為你的圖片資源
                                contentDescription = "職業配對遊戲圖片",
                                modifier = Modifier
                                    .fillMaxWidth() // 圖片寬度填滿可用空間
                                    .height(200.dp) // 圖片高度
                                    .padding(start = 55.dp) // 左邊的間隔，可以根據需要調整
                            )
                            Spacer(modifier = Modifier.height(16.dp)) // 圖片和按鈕之間的間隔

                            RectangularButton(text = "         職業配對         ", modifier = Modifier.fillMaxWidth()
                                .padding(start = 30.dp),height = 200.dp) {
                                navController.navigate("GameScreen")
                            }
                        }
                    }

                    Box {
                        Column {
                            // 日常用品配對遊戲按鈕的圖片與按鈕
                            Image(
                                painter = painterResource(id = R.drawable.daily), // 替換為你的圖片資源
                                contentDescription = "日常用品配對遊戲圖片",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(start = 63.dp) // 左邊的間隔，可以根據需要調整
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            RectangularButton(text = "       日常用品配對       ",modifier = Modifier.fillMaxWidth(), height = 200.dp) {
                                navController.navigate("DailyGameScreen")
                            }
                        }
                    }
                    Box {
                        Column {
                            // 愛心家園元素配對遊戲按鈕的圖片與按鈕
                            Image(
                                painter = painterResource(id = R.drawable.logo), // 替換為你的圖片資源
                                contentDescription = "愛心家園元素配對遊戲圖片",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(start = 75.dp) // 左邊的間隔，可以根據需要調整
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            RectangularButton(text = "    愛心家園元素配對    ",modifier = Modifier.fillMaxWidth(), height = 200.dp) {
                                navController.navigate("HomeGameScreen")
                            }
                        }
                    }
                }
            }
            BackToHomeButton(navController, modifier = Modifier.offset(y = 50.dp))
        }
    }
}


// 顯示每張圖片的組件
@Composable
fun ImageCard(imageDescription: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()  // 每張圖片佔滿寬度
            .height(300.dp)  // 設定每張圖片的高度
            .background(Color.Gray), // 這裡可以替換成實際的圖片資源
        contentAlignment = Alignment.Center
    ) {
        Text(text = imageDescription, color = Color.White, fontSize = 16.sp)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController) {
    val (cards, setCards) = remember { mutableStateOf(generateCards()) }
    val (flippedCards, setFlippedCards) = remember { mutableStateOf(listOf<Card>()) }
    val (matchedCards, setMatchedCards) = remember { mutableStateOf(listOf<Card>()) }
    val scope = rememberCoroutineScope()
    var gameCompleted by remember { mutableStateOf(false) }

    // 遊戲結束檢查
    LaunchedEffect(matchedCards) {
        if (matchedCards.size == cards.size) {
            gameCompleted = true
        }
    }

    // 遊戲結束彈窗
    if (gameCompleted) {
        AlertDialog(
            onDismissRequest = { gameCompleted = false },
            title = { Text("恭喜過關！") },
            text = { Text("你成功配對了所有卡片") },
            confirmButton = {
                Button(onClick = {
                    setCards(generateCards())
                    setFlippedCards(emptyList())
                    setMatchedCards(emptyList())
                    gameCompleted = false
                }) {
                    Text("再玩一次")
                }
            },
            dismissButton = {
                Button(onClick = { navController.navigateUp() }) {
                    Text("返回主頁")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("配對遊戲") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_revert), contentDescription = "返回")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "已配對：${matchedCards.size / 2} / ${cards.size / 2}",
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(cards, key = { it.id }) { card ->
                    CardView(
                        card = card,
                        isFlipped = card in flippedCards,
                        isMatched = card in matchedCards,
                        onClick = {
                            if (flippedCards.size < 2 && card !in flippedCards && card !in matchedCards) {
                                setFlippedCards(flippedCards + card)

                                if (flippedCards.size == 1) {
                                    val firstCard = flippedCards.first()

                                    if (firstCard.image == card.image) {
                                        setMatchedCards(matchedCards + firstCard + card)
                                        setFlippedCards(emptyList())
                                    } else {
                                        scope.launch {
                                            delay(1000)
                                            setFlippedCards(emptyList())
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyGameScreen(navController: NavController) {
    val (cards, setCards) = remember { mutableStateOf(generateDailyCards()) }
    val (flippedCards, setFlippedCards) = remember { mutableStateOf(listOf<Card>()) }
    val (matchedCards, setMatchedCards) = remember { mutableStateOf(listOf<Card>()) }
    val scope = rememberCoroutineScope()
    var gameCompleted by remember { mutableStateOf(false) }

    // 遊戲結束檢查
    LaunchedEffect(matchedCards) {
        if (matchedCards.size == cards.size) {
            gameCompleted = true
        }
    }

    // 遊戲結束彈窗
    if (gameCompleted) {
        AlertDialog(
            onDismissRequest = { gameCompleted = false },
            title = { Text("恭喜過關！") },
            text = { Text("你成功配對了所有卡片") },
            confirmButton = {
                Button(onClick = {
                    setCards(generateDailyCards())
                    setFlippedCards(emptyList())
                    setMatchedCards(emptyList())
                    gameCompleted = false
                }) {
                    Text("再玩一次")
                }
            },
            dismissButton = {
                Button(onClick = { navController.navigateUp() }) {
                    Text("返回主頁")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("配對遊戲") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_revert), contentDescription = "返回")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "已配對：${matchedCards.size / 2} / ${cards.size / 2}",
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(cards, key = { it.id }) { card ->
                    CardView(
                        card = card,
                        isFlipped = card in flippedCards,
                        isMatched = card in matchedCards,
                        onClick = {
                            if (flippedCards.size < 2 && card !in flippedCards && card !in matchedCards) {
                                setFlippedCards(flippedCards + card)

                                if (flippedCards.size == 1) {
                                    val firstCard = flippedCards.first()

                                    if (firstCard.image == card.image) {
                                        setMatchedCards(matchedCards + firstCard + card)
                                        setFlippedCards(emptyList())
                                    } else {
                                        scope.launch {
                                            delay(1000)
                                            setFlippedCards(emptyList())
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGameScreen(navController: NavController) {
    val (cards, setCards) = remember { mutableStateOf(generateHomeCards()) }
    val (flippedCards, setFlippedCards) = remember { mutableStateOf(listOf<Card>()) }
    val (matchedCards, setMatchedCards) = remember { mutableStateOf(listOf<Card>()) }
    val scope = rememberCoroutineScope()
    var gameCompleted by remember { mutableStateOf(false) }

    // 遊戲結束檢查
    LaunchedEffect(matchedCards) {
        if (matchedCards.size == cards.size) {
            gameCompleted = true
        }
    }

    // 遊戲結束彈窗
    if (gameCompleted) {
        AlertDialog(
            onDismissRequest = { gameCompleted = false },
            title = { Text("恭喜過關！") },
            text = { Text("你成功配對了所有卡片") },
            confirmButton = {
                Button(onClick = {
                    setCards(generateHomeCards())
                    setFlippedCards(emptyList())
                    setMatchedCards(emptyList())
                    gameCompleted = false
                }) {
                    Text("再玩一次")
                }
            },
            dismissButton = {
                Button(onClick = { navController.navigateUp() }) {
                    Text("返回主頁")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("配對遊戲") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_revert), contentDescription = "返回")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "已配對：${matchedCards.size / 2} / ${cards.size / 2}",
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(cards, key = { it.id }) { card ->
                    CardView(
                        card = card,
                        isFlipped = card in flippedCards,
                        isMatched = card in matchedCards,
                        onClick = {
                            if (flippedCards.size < 2 && card !in flippedCards && card !in matchedCards) {
                                setFlippedCards(flippedCards + card)

                                if (flippedCards.size == 1) {
                                    val firstCard = flippedCards.first()

                                    if (firstCard.image == card.image) {
                                        setMatchedCards(matchedCards + firstCard + card)
                                        setFlippedCards(emptyList())
                                    } else {
                                        scope.launch {
                                            delay(1000)
                                            setFlippedCards(emptyList())
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun CardView(
    card: Card,
    isFlipped: Boolean,
    isMatched: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .background(
                color = when {
                    isMatched -> Color.Green.copy(alpha = 0.3f)
                    isFlipped -> Color.White
                    else -> Color(0xFF005757)
                },
                shape = RoundedCornerShape(8.dp)
            )
            .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isFlipped || isMatched) {
            Image(
                painter = painterResource(id = card.imageResourceId),
                contentDescription = card.image,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun RectangularButton(
    text: String,
    height: Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val customFont = FontFamily(
        Font(R.font.fontttt) // 使用 fontttt.ttf 字體
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFC4E1FF) // 设置按钮的背景颜色
        )
    ) {
        Text(text = text, fontSize = 30.sp,color = Color.Black,fontFamily = customFont)

    }
}

// 卡片類別
data class Card(
    val id: Int,
    val image: String,
    val imageResourceId: Int
)

// 卡片對生成函數
fun generateCards(): List<Card> {
    val cardPairs = listOf(
        CardPair("牙刷", R.drawable.aa),
        CardPair("牙膏", R.drawable.bb),
        CardPair("消防員", R.drawable.cc),
        CardPair("滅火器", R.drawable.dd),
        CardPair("手機", R.drawable.ee),
        CardPair("耳機", R.drawable.ff),
        CardPair("書本", R.drawable.gg),
        CardPair("筆", R.drawable.hh)
    )

    return cardPairs.flatMap { pair ->
        listOf(
            Card(
                id = System.currentTimeMillis().toInt() + pair.name.hashCode(),
                image = pair.name,
                imageResourceId = pair.imageResourceId
            ),
            Card(
                id = System.currentTimeMillis().toInt() + pair.name.hashCode() + 1,
                image = pair.name,
                imageResourceId = pair.imageResourceId
            )
        )
    }.shuffled() // 打亂卡片順序
}
fun generateDailyCards(): List<Card> {
    val cardPairs = listOf(
        CardPair("牙刷", R.drawable.qq),
        CardPair("牙膏", R.drawable.u),
        CardPair("消防員", R.drawable.rr),
        CardPair("滅火器", R.drawable.vv),
        CardPair("手機", R.drawable.s),
        CardPair("耳機", R.drawable.w),
        CardPair("書本", R.drawable.t),
        CardPair("筆", R.drawable.xx)
    )

    return cardPairs.flatMap { pair ->
        listOf(
            Card(
                id = System.currentTimeMillis().toInt() + pair.name.hashCode(),
                image = pair.name,
                imageResourceId = pair.imageResourceId
            ),
            Card(
                id = System.currentTimeMillis().toInt() + pair.name.hashCode() + 1,
                image = pair.name,
                imageResourceId = pair.imageResourceId
            )
        )
    }.shuffled() // 打亂卡片順序
}
fun generateHomeCards(): List<Card> {
    val cardPairs = listOf(
        CardPair("牙刷", R.drawable.flower),
        CardPair("牙膏", R.drawable.house),
        CardPair("消防員", R.drawable.lighthouse),
        CardPair("滅火器", R.drawable.rainbow),
        CardPair("手機", R.drawable.river),
        CardPair("耳機", R.drawable.star),
        CardPair("書本", R.drawable.sun),
        CardPair("筆", R.drawable.tree)
    )

    return cardPairs.flatMap { pair ->
        listOf(
            Card(
                id = System.currentTimeMillis().toInt() + pair.name.hashCode(),
                image = pair.name,
                imageResourceId = pair.imageResourceId
            ),
            Card(
                id = System.currentTimeMillis().toInt() + pair.name.hashCode() + 1,
                image = pair.name,
                imageResourceId = pair.imageResourceId
            )
        )
    }.shuffled() // 打亂卡片順序
}

// 卡片對輔助類別
data class CardPair(val name: String, val imageResourceId: Int)