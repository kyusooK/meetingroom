<template>
    <v-card outlined>
        <v-card-title>
            CreateReservation
        </v-card-title>

        <v-card-text>
            <Date label="StartDate" v-model="value.startDate" :editMode="editMode"/>
            <Date label="EndDate" v-model="value.endDate" :editMode="editMode"/>
            <String label="MeetingName" v-model="value.meetingName" :editMode="editMode"/>
            <ReservationStatus offline label="ReservationStatus" v-model="value.reservationStatus" :editMode="editMode" @change="change"/>
            <String label="UserId" v-model="value.userId" :editMode="editMode"/>
            <FacilityRequestId offline label="FacilityRequestId" v-model="value.facilityRequestId" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="createReservation"
            >
                CreateReservation
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
        name: 'CreateReservationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.startDate = '2024-12-27';
            this.value.endDate = '2024-12-27';
            this.value.meetingName = '';
            this.value.reservationStatus = {};
            this.value.userId = '';
            this.value.facilityRequestId = {};
        },
        watch: {
        },
        methods: {
            createReservation() {
                this.$emit('createReservation', this.value);
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

