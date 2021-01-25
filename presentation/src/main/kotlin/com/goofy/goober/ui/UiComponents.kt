package com.goofy.goober.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goofy.goober.domain.model.Category
import com.goofy.goober.domain.model.Excerpt
import com.goofy.goober.domain.model.Link
import com.goofy.goober.domain.model.Links
import com.goofy.goober.domain.model.Post
import com.goofy.goober.domain.model.Title

@Composable
fun CategoryRow(category: Category, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable(onClick = onClick).padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
        ) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.weight(0.95f))
            IconButton(onClick) {
                Icon(Icons.Filled.ArrowForward)
            }
        }
    }
}

@Composable
fun PostRow(post: Post) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = post.title.rendered, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.preferredHeight(14.dp))
        Text(text = post.excerpt.rendered, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun ProgressBarRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.preferredHeight(32.dp).preferredWidth(32.dp),
            color = Color.Magenta,
            strokeWidth = 3.dp
        )
    }
}

@Preview
@Composable
fun DefaultPostRowPreview() {
    MaterialTheme {
        PostRow(
            Post(
                excerpt = Excerpt(
                    "Carefully design your queries to pull in only the needed " +
                        "properties from each resource to make your application faster to use and " +
                        "more efficient to run."
                ),
                title = Title("This is a Title"),
                id = 1_000,
                link = "www.go.com"
            )
        )
    }
}

@Preview
@Composable
fun DefaultCategoryRowPreview() {
    MaterialTheme {
        CategoryRow(
            Category(
                id = 123,
                name = "Blue Rose",
                links = Links(
                    posts = listOf(Link("posts.com")),
                    parent = listOf(Link("parent.com"))
                )
            ),
            onClick = {}
        )
    }
}

@Preview
@Composable
fun DefaultLoadingProgressRowPreview() {
    MaterialTheme {
        ProgressBarRow()
    }
}
