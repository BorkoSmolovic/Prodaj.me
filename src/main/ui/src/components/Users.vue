<template>
    <div class="container">
        <h2>Ads</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Ad Id</th>
                <th>Name</th>
                <th>Description</th>
                <th>Seller</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="item in users" :key="item.id">
                  <td>{{ item.id }}</td>
                  <td>{{ item.firstName }}</td>
                  <td>{{ item.lastName }}</td>
                  <td>{{ item.email }}</td>
              </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

    export default {
        name: 'Users',
        props: ['users']
     }
</script>