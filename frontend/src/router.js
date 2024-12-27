
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ReservationmanagementReservationManager from "./components/listers/ReservationmanagementReservationCards"
import ReservationmanagementReservationDetail from "./components/listers/ReservationmanagementReservationDetail"

import GetMeetingRoomView from "./components/GetMeetingRoomView"
import GetMeetingRoomViewDetail from "./components/GetMeetingRoomViewDetail"
import CalendarintegrationNotificationManager from "./components/listers/CalendarintegrationNotificationCards"
import CalendarintegrationNotificationDetail from "./components/listers/CalendarintegrationNotificationDetail"

import AccesscontrolUserManager from "./components/listers/AccesscontrolUserCards"
import AccesscontrolUserDetail from "./components/listers/AccesscontrolUserDetail"

import ResourcemanagementFacilityRequestManager from "./components/listers/ResourcemanagementFacilityRequestCards"
import ResourcemanagementFacilityRequestDetail from "./components/listers/ResourcemanagementFacilityRequestDetail"

import GetFacilityView from "./components/GetFacilityView"
import GetFacilityViewDetail from "./components/GetFacilityViewDetail"
import StatisticsReservationStatisticsManager from "./components/listers/StatisticsReservationStatisticsCards"
import StatisticsReservationStatisticsDetail from "./components/listers/StatisticsReservationStatisticsDetail"
import StatisticsFacilityStatisticsManager from "./components/listers/StatisticsFacilityStatisticsCards"
import StatisticsFacilityStatisticsDetail from "./components/listers/StatisticsFacilityStatisticsDetail"

import RoomUsageView from "./components/RoomUsageView"
import RoomUsageViewDetail from "./components/RoomUsageViewDetail"
import FacilityHistoryView from "./components/FacilityHistoryView"
import FacilityHistoryViewDetail from "./components/FacilityHistoryViewDetail"
import RoommanagementMeetingRoomManager from "./components/listers/RoommanagementMeetingRoomCards"
import RoommanagementMeetingRoomDetail from "./components/listers/RoommanagementMeetingRoomDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/reservationmanagements/reservations',
                name: 'ReservationmanagementReservationManager',
                component: ReservationmanagementReservationManager
            },
            {
                path: '/reservationmanagements/reservations/:id',
                name: 'ReservationmanagementReservationDetail',
                component: ReservationmanagementReservationDetail
            },

            {
                path: '/reservationmanagements/getMeetingRooms',
                name: 'GetMeetingRoomView',
                component: GetMeetingRoomView
            },
            {
                path: '/reservationmanagements/getMeetingRooms/:id',
                name: 'GetMeetingRoomViewDetail',
                component: GetMeetingRoomViewDetail
            },
            {
                path: '/calendarintegrations/notifications',
                name: 'CalendarintegrationNotificationManager',
                component: CalendarintegrationNotificationManager
            },
            {
                path: '/calendarintegrations/notifications/:id',
                name: 'CalendarintegrationNotificationDetail',
                component: CalendarintegrationNotificationDetail
            },

            {
                path: '/accesscontrols/users',
                name: 'AccesscontrolUserManager',
                component: AccesscontrolUserManager
            },
            {
                path: '/accesscontrols/users/:id',
                name: 'AccesscontrolUserDetail',
                component: AccesscontrolUserDetail
            },

            {
                path: '/resourcemanagements/facilityRequests',
                name: 'ResourcemanagementFacilityRequestManager',
                component: ResourcemanagementFacilityRequestManager
            },
            {
                path: '/resourcemanagements/facilityRequests/:id',
                name: 'ResourcemanagementFacilityRequestDetail',
                component: ResourcemanagementFacilityRequestDetail
            },

            {
                path: '/resourcemanagements/getFacilities',
                name: 'GetFacilityView',
                component: GetFacilityView
            },
            {
                path: '/resourcemanagements/getFacilities/:id',
                name: 'GetFacilityViewDetail',
                component: GetFacilityViewDetail
            },
            {
                path: '/statistics/reservationStatistics',
                name: 'StatisticsReservationStatisticsManager',
                component: StatisticsReservationStatisticsManager
            },
            {
                path: '/statistics/reservationStatistics/:id',
                name: 'StatisticsReservationStatisticsDetail',
                component: StatisticsReservationStatisticsDetail
            },
            {
                path: '/statistics/facilityStatistics',
                name: 'StatisticsFacilityStatisticsManager',
                component: StatisticsFacilityStatisticsManager
            },
            {
                path: '/statistics/facilityStatistics/:id',
                name: 'StatisticsFacilityStatisticsDetail',
                component: StatisticsFacilityStatisticsDetail
            },

            {
                path: '/statistics/roomUsages',
                name: 'RoomUsageView',
                component: RoomUsageView
            },
            {
                path: '/statistics/roomUsages/:id',
                name: 'RoomUsageViewDetail',
                component: RoomUsageViewDetail
            },
            {
                path: '/statistics/facilityHistories',
                name: 'FacilityHistoryView',
                component: FacilityHistoryView
            },
            {
                path: '/statistics/facilityHistories/:id',
                name: 'FacilityHistoryViewDetail',
                component: FacilityHistoryViewDetail
            },
            {
                path: '/roommanagements/meetingRooms',
                name: 'RoommanagementMeetingRoomManager',
                component: RoommanagementMeetingRoomManager
            },
            {
                path: '/roommanagements/meetingRooms/:id',
                name: 'RoommanagementMeetingRoomDetail',
                component: RoommanagementMeetingRoomDetail
            },



    ]
})
