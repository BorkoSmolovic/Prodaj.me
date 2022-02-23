export async function getAllAds() {

    const response = await fetch('/api/ads');
    return await response.json();
}

export async function createAd(data) {
    const response = await fetch(`/api/ad`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
      })
    return await response.json();
}

export async function updateAd(data) {
    const response = await fetch(`/api/ad`, {
        method: 'UPDATE',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
      })
    return await response.json();
}