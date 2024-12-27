<template>
    <v-card outlined>
        <v-card-title>
            AuthorizeReservation
        </v-card-title>

        <v-card-text>
            <Number label="UserId" v-model="value.userId" :editMode="editMode"/>
            <Number label="RoomId" v-model="value.roomId" :editMode="editMode"/>
            <Date label="StartTime" v-model="value.startTime" :editMode="editMode"/>
            <Date label="EndTime" v-model="value.endTime" :editMode="editMode"/>
            <Number label="ProxyUserId" v-model="value.proxyUserId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="authorizeReservation"
            >
                AuthorizeReservation
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'AuthorizeReservationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.userId = 0;
            this.value.roomId = 0;
            this.value.startTime = {};
            this.value.endTime = {};
            this.value.proxyUserId = 0;
        },
        watch: {
        },
        methods: {
            authorizeReservation() {
                this.$emit('authorizeReservation', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

