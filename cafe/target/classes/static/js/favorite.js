function addFavorite(institutionId, dishId, userId, index) {
    fetch(`/api/v1/apps/dishes/addFavorite/${institutionId}/${dishId}/${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ institutionId: institutionId, dishId: dishId, userId: userId })
    })
    .then(response => {
        if (response.ok) {
            const element = document.getElementById('btn' + index);
            element.remove();
            alert('Dish added to favorites!');
        } else {
            alert('Failed to add dish to favorites.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}