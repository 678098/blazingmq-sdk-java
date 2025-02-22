/*
 * Copyright 2022 Bloomberg Finance L.P.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bloomberg.bmq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.bloomberg.bmq.ResultCodes.AckCode;
import com.bloomberg.bmq.ResultCodes.AckResult;
import com.bloomberg.bmq.ResultCodes.CloseQueueCode;
import com.bloomberg.bmq.ResultCodes.CloseQueueResult;
import com.bloomberg.bmq.ResultCodes.ConfigureQueueCode;
import com.bloomberg.bmq.ResultCodes.ConfigureQueueResult;
import com.bloomberg.bmq.ResultCodes.GenericCode;
import com.bloomberg.bmq.ResultCodes.GenericResult;
import com.bloomberg.bmq.ResultCodes.OpenQueueCode;
import com.bloomberg.bmq.ResultCodes.OpenQueueResult;
import org.junit.Test;

public class ResultCodesTest {
    // ================================================================
    // GenericResult tests
    // ================================================================
    public abstract static class BaseTest {
        public void isSuccessTest(GenericCode obj) {
            assertFalse(obj.isSuccess());
        }

        public void isFailureTest(GenericCode obj) {
            assertFalse(obj.isFailure());
        }

        public void isCanceledTest(GenericCode obj) {
            assertFalse(obj.isCanceled());
        }

        public void isInvalidArgumentTest(GenericCode obj) {
            assertFalse(obj.isInvalidArgument());
        }

        public void isNotConnectedTest(GenericCode obj) {
            assertFalse(obj.isNotConnected());
        }

        public void isNotReadyTest(GenericCode obj) {
            assertFalse(obj.isNotReady());
        }

        public void isNotSupportedTest(GenericCode obj) {
            assertFalse(obj.isNotSupproted());
        }

        public void isRefusedTest(GenericCode obj) {
            assertFalse(obj.isRefused());
        }

        public void isTimeoutTest(GenericCode obj) {
            assertFalse(obj.isTimeout());
        }

        public void isUnknownTest(GenericCode obj) {
            assertFalse(obj.isUnknown());
        }
    }

    public static class SuccessTest extends BaseTest {
        @Override
        public void isSuccessTest(GenericCode obj) {
            assertTrue(obj.isSuccess());
        }
    }

    public abstract static class FailureStatusTest extends BaseTest {
        @Override
        public void isFailureTest(GenericCode obj) {
            assertTrue(obj.isFailure());
        }
    }

    public static class CanceledTest extends FailureStatusTest {
        @Override
        public void isCanceledTest(GenericCode obj) {
            assertTrue(obj.isCanceled());
        }
    }

    public static class InvalidArgumentTest extends FailureStatusTest {
        @Override
        public void isInvalidArgumentTest(GenericCode obj) {
            assertTrue(obj.isInvalidArgument());
        }
    }

    public static class NotConnectedTest extends FailureStatusTest {
        @Override
        public void isNotConnectedTest(GenericCode obj) {
            assertTrue(obj.isNotConnected());
        }
    }

    public static class NotReadyTest extends FailureStatusTest {
        @Override
        public void isNotReadyTest(GenericCode obj) {
            assertTrue(obj.isNotReady());
        }
    }

    public static class NotSupportedTest extends FailureStatusTest {
        @Override
        public void isNotSupportedTest(GenericCode obj) {
            assertTrue(obj.isNotSupproted());
        }
    }

    public static class RefusedTest extends FailureStatusTest {
        @Override
        public void isRefusedTest(GenericCode obj) {
            assertTrue(obj.isRefused());
        }
    }

    public static class TimeoutTest extends FailureStatusTest {
        @Override
        public void isTimeoutTest(GenericCode obj) {
            assertTrue(obj.isTimeout());
        }
    }

    public static class UnknownTest extends FailureStatusTest {
        @Override
        public void isUnknownTest(GenericCode obj) {
            assertTrue(obj.isUnknown());
        }
    }

    public static class GenericResultTest {
        public static void checkPredicates(BaseTest test, GenericCode obj) {
            test.isSuccessTest(obj);
            test.isFailureTest(obj);
            test.isCanceledTest(obj);
            test.isInvalidArgumentTest(obj);
            test.isNotConnectedTest(obj);
            test.isNotReadyTest(obj);
            test.isNotSupportedTest(obj);
            test.isRefusedTest(obj);
            test.isTimeoutTest(obj);
            test.isUnknownTest(obj);
        }

        public static void checkIfGeneric(GenericCode obj) {
            assertEquals(obj, obj.getGeneralResult());
        }

        public static void checkIfNotGeneric(GenericCode obj) {
            assertNotEquals(obj, obj.getGeneralResult());
        }

        public static void checkSuccess(GenericCode obj) {
            GenericResultTest.checkPredicates(new SuccessTest(), obj);
        }

        public static void checkTimeout(GenericCode obj) {
            GenericResultTest.checkPredicates(new TimeoutTest(), obj);
        }

        public static void checkNotConnected(GenericCode obj) {
            GenericResultTest.checkPredicates(new NotConnectedTest(), obj);
        }

        public static void checkCanceled(GenericCode obj) {
            GenericResultTest.checkPredicates(new CanceledTest(), obj);
        }

        public static void checkNotSupported(GenericCode obj) {
            GenericResultTest.checkPredicates(new NotSupportedTest(), obj);
        }

        public static void checkRefused(GenericCode obj) {
            GenericResultTest.checkPredicates(new RefusedTest(), obj);
        }

        public static void checkInvalidArgument(GenericCode obj) {
            GenericResultTest.checkPredicates(new InvalidArgumentTest(), obj);
        }

        public static void checkNotReady(GenericCode obj) {
            GenericResultTest.checkPredicates(new NotReadyTest(), obj);
        }

        public static void checkUnknown(GenericCode obj) {
            GenericResultTest.checkPredicates(new UnknownTest(), obj);
        }

        @Test
        public void timeOutTest() {
            GenericResultTest.checkIfGeneric(GenericResult.TIMEOUT);
            GenericResultTest.checkTimeout(GenericResult.TIMEOUT);
        }

        @Test
        public void notConnectedTest() {
            GenericResultTest.checkIfGeneric(GenericResult.NOT_CONNECTED);
            GenericResultTest.checkNotConnected(GenericResult.NOT_CONNECTED);
        }

        @Test
        public void canceledTest() {
            GenericResultTest.checkIfGeneric(GenericResult.CANCELED);
            GenericResultTest.checkCanceled(GenericResult.CANCELED);
        }

        @Test
        public void notSupportedTest() {
            GenericResultTest.checkIfGeneric(GenericResult.NOT_SUPPORTED);
            GenericResultTest.checkNotSupported(GenericResult.NOT_SUPPORTED);
        }

        @Test
        public void refusedTest() {
            GenericResultTest.checkIfGeneric(GenericResult.REFUSED);
            GenericResultTest.checkRefused(GenericResult.REFUSED);
        }

        @Test
        public void invalidArgumentTest() {
            GenericResultTest.checkIfGeneric(GenericResult.INVALID_ARGUMENT);
            GenericResultTest.checkInvalidArgument(GenericResult.INVALID_ARGUMENT);
        }

        @Test
        public void notReadyTest() {
            GenericResultTest.checkIfGeneric(GenericResult.NOT_READY);
            GenericResultTest.checkNotReady(GenericResult.NOT_READY);
        }

        @Test
        public void unknownTest() {
            GenericResultTest.checkIfGeneric(GenericResult.UNKNOWN);
            GenericResultTest.checkUnknown(GenericResult.UNKNOWN);
        }
    }

    // ================================================================
    // OpenQueueResult tests
    // ================================================================

    public static class OpenQueueResultTest {
        public static class OpenQueueGenericTest {
            public void isAlreadyOpenedTest(OpenQueueCode obj) {
                assertFalse(obj.isAlreadyOpened());
            }

            public void isAlreadyInProgressTest(OpenQueueCode obj) {
                assertFalse(obj.isAlreadyInProgress());
            }

            public void isInvalidUriTest(OpenQueueCode obj) {
                assertFalse(obj.isInvalidUri());
            }

            public void isInvalidFlagsTest(OpenQueueCode obj) {
                assertFalse(obj.isInvalidFlags());
            }

            public void isQueueIdNotUniqueTest(OpenQueueCode obj) {
                assertFalse(obj.isQueueIdNotUnique());
            }
        }

        public static class AlreadyOpenedTest extends OpenQueueGenericTest {
            @Override
            public void isAlreadyOpenedTest(OpenQueueCode obj) {
                assertTrue(obj.isAlreadyOpened());
            }
        }

        public static class AlreadyInProgressTest extends OpenQueueGenericTest {
            public void isAlreadyInProgressTest(OpenQueueCode obj) {
                assertTrue(obj.isAlreadyInProgress());
            }
        }

        public static class InvalidUriTest extends OpenQueueGenericTest {
            @Override
            public void isInvalidUriTest(OpenQueueCode obj) {
                assertTrue(obj.isInvalidUri());
            }
        }

        public static class InvalidFlagsTest extends OpenQueueGenericTest {
            @Override
            public void isInvalidFlagsTest(OpenQueueCode obj) {
                assertTrue(obj.isInvalidFlags());
            }
        }

        public static class QueueIdNotUniqueTest extends OpenQueueGenericTest {
            @Override
            public void isQueueIdNotUniqueTest(OpenQueueCode obj) {
                assertTrue(obj.isQueueIdNotUnique());
            }
        }

        public static void checkExtraPredicates(OpenQueueGenericTest test, OpenQueueCode obj) {
            test.isAlreadyInProgressTest(obj);
            test.isAlreadyOpenedTest(obj);
            test.isInvalidFlagsTest(obj);
            test.isInvalidUriTest(obj);
            test.isQueueIdNotUniqueTest(obj);
        }

        public static void checkGeneric(OpenQueueCode obj) {
            OpenQueueResultTest.checkExtraPredicates(new OpenQueueGenericTest(), obj);
        }

        public static void checkAlreadyInProgress(OpenQueueCode obj) {
            OpenQueueResultTest.checkExtraPredicates(new AlreadyInProgressTest(), obj);
        }

        public static void checkAlreadyOpened(OpenQueueCode obj) {
            OpenQueueResultTest.checkExtraPredicates(new AlreadyOpenedTest(), obj);
        }

        public static void checkInvalidFlags(OpenQueueCode obj) {
            OpenQueueResultTest.checkExtraPredicates(new InvalidFlagsTest(), obj);
        }

        public static void checkInvalidUri(OpenQueueCode obj) {
            OpenQueueResultTest.checkExtraPredicates(new InvalidUriTest(), obj);
        }

        public static void checkQueueIdNotUnique(OpenQueueCode obj) {
            OpenQueueResultTest.checkExtraPredicates(new QueueIdNotUniqueTest(), obj);
        }

        @Test
        public void successTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.SUCCESS);
            GenericResultTest.checkSuccess(OpenQueueResult.SUCCESS);
            OpenQueueResultTest.checkGeneric(OpenQueueResult.SUCCESS);
        }

        @Test
        public void timeOutTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.TIMEOUT);
            GenericResultTest.checkTimeout(OpenQueueResult.TIMEOUT);
            OpenQueueResultTest.checkGeneric(OpenQueueResult.TIMEOUT);
        }

        @Test
        public void notConnectedTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.NOT_CONNECTED);
            GenericResultTest.checkNotConnected(OpenQueueResult.NOT_CONNECTED);
            OpenQueueResultTest.checkGeneric(OpenQueueResult.NOT_CONNECTED);
        }

        @Test
        public void canceledTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.CANCELED);
            GenericResultTest.checkCanceled(OpenQueueResult.CANCELED);
            OpenQueueResultTest.checkGeneric(OpenQueueResult.CANCELED);
        }

        @Test
        public void notSupportedTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.NOT_SUPPORTED);
            GenericResultTest.checkNotSupported(OpenQueueResult.NOT_SUPPORTED);
            OpenQueueResultTest.checkGeneric(OpenQueueResult.NOT_SUPPORTED);
        }

        @Test
        public void refusedTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.REFUSED);
            GenericResultTest.checkRefused(OpenQueueResult.REFUSED);
            OpenQueueResultTest.checkGeneric(OpenQueueResult.REFUSED);
        }

        @Test
        public void invalidArgumentTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.INVALID_ARGUMENT);
            GenericResultTest.checkInvalidArgument(OpenQueueResult.INVALID_ARGUMENT);
            OpenQueueResultTest.checkGeneric(OpenQueueResult.INVALID_ARGUMENT);
        }

        @Test
        public void notReadyTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.NOT_READY);
            GenericResultTest.checkNotReady(OpenQueueResult.NOT_READY);
            OpenQueueResultTest.checkGeneric(OpenQueueResult.NOT_READY);
        }

        @Test
        public void unknownTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.UNKNOWN);
            GenericResultTest.checkUnknown(OpenQueueResult.UNKNOWN);
            OpenQueueResultTest.checkGeneric(OpenQueueResult.UNKNOWN);
        }

        @Test
        public void alreadyInProgressTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.ALREADY_IN_PROGRESS);
            OpenQueueResultTest.checkAlreadyInProgress(OpenQueueResult.ALREADY_IN_PROGRESS);
        }

        @Test
        public void alreadyOpenedTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.ALREADY_OPENED);
            OpenQueueResultTest.checkAlreadyOpened(OpenQueueResult.ALREADY_OPENED);
        }

        @Test
        public void invalidFlagsTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.INVALID_FLAGS);
            OpenQueueResultTest.checkInvalidFlags(OpenQueueResult.INVALID_FLAGS);
        }

        @Test
        public void invalidUriTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.INVALID_URI);
            OpenQueueResultTest.checkInvalidUri(OpenQueueResult.INVALID_URI);
        }

        @Test
        public void queueIdNotUniqueTest() {
            GenericResultTest.checkIfNotGeneric(OpenQueueResult.QUEUE_ID_NOT_UNIQUE);
            OpenQueueResultTest.checkQueueIdNotUnique(OpenQueueResult.QUEUE_ID_NOT_UNIQUE);
        }
    }

    // ================================================================
    // ConfigureQueueResult tests
    // ================================================================
    public static class ConfigureQueueResultTest {
        public static class ConfigureQueueGenericTest {
            public void isAlreadyInProgressTest(ConfigureQueueCode obj) {
                assertFalse(obj.isAlreadyInProgress());
            }

            public void isInvalidQueueTest(ConfigureQueueCode obj) {
                assertFalse(obj.isInvalidQueue());
            }
        }

        public static class AlreadyInProgressTest extends ConfigureQueueGenericTest {
            @Override
            public void isAlreadyInProgressTest(ConfigureQueueCode obj) {
                assertTrue(obj.isAlreadyInProgress());
            }
        }

        public static class InvalidQueueTest extends ConfigureQueueGenericTest {
            @Override
            public void isInvalidQueueTest(ConfigureQueueCode obj) {
                assertTrue(obj.isInvalidQueue());
            }
        }

        public static void checkPredicates(ConfigureQueueGenericTest test, ConfigureQueueCode obj) {
            test.isAlreadyInProgressTest(obj);
            test.isInvalidQueueTest(obj);
        }

        public static void checkGeneric(ConfigureQueueCode obj) {
            ConfigureQueueResultTest.checkPredicates(new ConfigureQueueGenericTest(), obj);
        }

        public static void checkAlreadyInProgress(ConfigureQueueCode obj) {
            ConfigureQueueResultTest.checkPredicates(new AlreadyInProgressTest(), obj);
        }

        public static void checkAlreadyOpenedTest(ConfigureQueueCode obj) {
            ConfigureQueueResultTest.checkPredicates(new InvalidQueueTest(), obj);
        }

        @Test
        public void successTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.SUCCESS);
            GenericResultTest.checkSuccess(ConfigureQueueResult.SUCCESS);
            ConfigureQueueResultTest.checkGeneric(ConfigureQueueResult.SUCCESS);
        }

        @Test
        public void timeOutTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.TIMEOUT);
            GenericResultTest.checkTimeout(ConfigureQueueResult.TIMEOUT);
            ConfigureQueueResultTest.checkGeneric(ConfigureQueueResult.TIMEOUT);
        }

        @Test
        public void notConnectedTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.NOT_CONNECTED);
            GenericResultTest.checkNotConnected(ConfigureQueueResult.NOT_CONNECTED);
            ConfigureQueueResultTest.checkGeneric(ConfigureQueueResult.NOT_CONNECTED);
        }

        @Test
        public void canceledTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.CANCELED);
            GenericResultTest.checkCanceled(ConfigureQueueResult.CANCELED);
            ConfigureQueueResultTest.checkGeneric(ConfigureQueueResult.CANCELED);
        }

        @Test
        public void notSupportedTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.NOT_SUPPORTED);
            GenericResultTest.checkNotSupported(ConfigureQueueResult.NOT_SUPPORTED);
            ConfigureQueueResultTest.checkGeneric(ConfigureQueueResult.NOT_SUPPORTED);
        }

        @Test
        public void refusedTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.REFUSED);
            GenericResultTest.checkRefused(ConfigureQueueResult.REFUSED);
            ConfigureQueueResultTest.checkGeneric(ConfigureQueueResult.REFUSED);
        }

        @Test
        public void invalidArgumentTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.INVALID_ARGUMENT);
            GenericResultTest.checkInvalidArgument(ConfigureQueueResult.INVALID_ARGUMENT);
            ConfigureQueueResultTest.checkGeneric(ConfigureQueueResult.INVALID_ARGUMENT);
        }

        @Test
        public void notReadyTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.NOT_READY);
            GenericResultTest.checkNotReady(ConfigureQueueResult.NOT_READY);
            ConfigureQueueResultTest.checkGeneric(ConfigureQueueResult.NOT_READY);
        }

        @Test
        public void unknownTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.UNKNOWN);
            GenericResultTest.checkUnknown(ConfigureQueueResult.UNKNOWN);
            ConfigureQueueResultTest.checkGeneric(ConfigureQueueResult.UNKNOWN);
        }

        @Test
        public void alreadyInProgressTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.ALREADY_IN_PROGRESS);
            ConfigureQueueResultTest.checkAlreadyInProgress(
                    ConfigureQueueResult.ALREADY_IN_PROGRESS);
        }

        @Test
        public void alreadyOpenedTest() {
            GenericResultTest.checkIfNotGeneric(ConfigureQueueResult.INVALID_QUEUE);
            ConfigureQueueResultTest.checkAlreadyOpenedTest(ConfigureQueueResult.INVALID_QUEUE);
        }
    }

    // ================================================================
    // CloseQueueResult tests
    // ================================================================
    public static class CloseQueueResultTest {
        public static class CloseQueueGenericTest {
            public void isAlreadyClosed(CloseQueueCode obj) {
                assertFalse(obj.isAlreadyClosed());
            }

            public void isAlreadyInProgressTest(CloseQueueCode obj) {
                assertFalse(obj.isAlreadyInProgress());
            }

            public void isUnknownQueueTest(CloseQueueCode obj) {
                assertFalse(obj.isUnknownQueue());
            }

            public void isInvalidQueueTest(CloseQueueCode obj) {
                assertFalse(obj.isInvalidQueue());
            }
        }

        public static class AlreadyClosedTest extends CloseQueueGenericTest {
            @Override
            public void isAlreadyClosed(CloseQueueCode obj) {
                assertTrue(obj.isAlreadyClosed());
            }
        }

        public static class AlreadyInProgressTest extends CloseQueueGenericTest {
            @Override
            public void isAlreadyInProgressTest(CloseQueueCode obj) {
                assertTrue(obj.isAlreadyInProgress());
            }
        }

        public static class UnknownQueueTest extends CloseQueueGenericTest {
            @Override
            public void isUnknownQueueTest(CloseQueueCode obj) {
                assertTrue(obj.isUnknownQueue());
            }
        }

        public static class InvalidQueueTest extends CloseQueueGenericTest {
            @Override
            public void isInvalidQueueTest(CloseQueueCode obj) {
                assertTrue(obj.isInvalidQueue());
            }
        }

        public static void checkPredicates(CloseQueueGenericTest test, CloseQueueResult obj) {
            test.isAlreadyClosed(obj);
            test.isAlreadyInProgressTest(obj);
            test.isUnknownQueueTest(obj);
            test.isInvalidQueueTest(obj);
        }

        public static void checkGeneric(CloseQueueResult obj) {
            CloseQueueResultTest.checkPredicates(new CloseQueueGenericTest(), obj);
        }

        public static void checkAlreadyClosed(CloseQueueResult obj) {
            CloseQueueResultTest.checkPredicates(new AlreadyClosedTest(), obj);
        }

        public static void checkAlreadyInProgress(CloseQueueResult obj) {
            CloseQueueResultTest.checkPredicates(new AlreadyInProgressTest(), obj);
        }

        public static void checkUnknownQueue(CloseQueueResult obj) {
            CloseQueueResultTest.checkPredicates(new UnknownQueueTest(), obj);
        }

        public static void checkInvalidQueue(CloseQueueResult obj) {
            CloseQueueResultTest.checkPredicates(new InvalidQueueTest(), obj);
        }

        @Test
        public void successTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.SUCCESS);
            GenericResultTest.checkSuccess(CloseQueueResult.SUCCESS);
            CloseQueueResultTest.checkGeneric(CloseQueueResult.SUCCESS);
        }

        @Test
        public void timeOutTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.TIMEOUT);
            GenericResultTest.checkTimeout(CloseQueueResult.TIMEOUT);
            CloseQueueResultTest.checkGeneric(CloseQueueResult.TIMEOUT);
        }

        @Test
        public void notConnectedTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.NOT_CONNECTED);
            GenericResultTest.checkNotConnected(CloseQueueResult.NOT_CONNECTED);
            CloseQueueResultTest.checkGeneric(CloseQueueResult.NOT_CONNECTED);
        }

        @Test
        public void canceledTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.CANCELED);
            GenericResultTest.checkCanceled(CloseQueueResult.CANCELED);
            CloseQueueResultTest.checkGeneric(CloseQueueResult.CANCELED);
        }

        @Test
        public void notSupportedTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.NOT_SUPPORTED);
            GenericResultTest.checkNotSupported(CloseQueueResult.NOT_SUPPORTED);
            CloseQueueResultTest.checkGeneric(CloseQueueResult.NOT_SUPPORTED);
        }

        @Test
        public void refusedTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.REFUSED);
            GenericResultTest.checkRefused(CloseQueueResult.REFUSED);
            CloseQueueResultTest.checkGeneric(CloseQueueResult.REFUSED);
        }

        @Test
        public void invalidArgumentTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.INVALID_ARGUMENT);
            GenericResultTest.checkInvalidArgument(CloseQueueResult.INVALID_ARGUMENT);
            CloseQueueResultTest.checkGeneric(CloseQueueResult.INVALID_ARGUMENT);
        }

        @Test
        public void notReadyTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.NOT_READY);
            GenericResultTest.checkNotReady(CloseQueueResult.NOT_READY);
            CloseQueueResultTest.checkGeneric(CloseQueueResult.NOT_READY);
        }

        @Test
        public void unknownTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.UNKNOWN);
            GenericResultTest.checkUnknown(CloseQueueResult.UNKNOWN);
            CloseQueueResultTest.checkGeneric(CloseQueueResult.UNKNOWN);
        }

        @Test
        public void alreadyClosedTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.ALREADY_CLOSED);
            CloseQueueResultTest.checkAlreadyClosed(CloseQueueResult.ALREADY_CLOSED);
        }

        @Test
        public void alreadyInProgressTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.ALREADY_IN_PROGRESS);
            CloseQueueResultTest.checkAlreadyInProgress(CloseQueueResult.ALREADY_IN_PROGRESS);
        }

        @Test
        public void unknownQueueTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.UNKNOWN_QUEUE);
            CloseQueueResultTest.checkUnknownQueue(CloseQueueResult.UNKNOWN_QUEUE);
        }

        @Test
        public void invalidQueueTest() {
            GenericResultTest.checkIfNotGeneric(CloseQueueResult.INVALID_QUEUE);
            CloseQueueResultTest.checkInvalidQueue(CloseQueueResult.INVALID_QUEUE);
        }
    }

    // ================================================================
    // AckResult tests
    // ================================================================
    public static class AckResultTest {
        public static class AckGenericTest {
            public void isLimitMessages(AckCode obj) {
                assertFalse(obj.isLimitMessages());
            }

            public void isLimitBytes(AckCode obj) {
                assertFalse(obj.isLimitBytes());
            }

            public void isStorageFailure(AckCode obj) {
                assertFalse(obj.isStorageFailure());
            }
        }

        public static class LimitMessagesTest extends AckGenericTest {
            @Override
            public void isLimitMessages(AckCode obj) {
                assertTrue(obj.isLimitMessages());
            }
        }

        public static class LimitBytesTest extends AckGenericTest {
            @Override
            public void isLimitBytes(AckCode obj) {
                assertTrue(obj.isLimitBytes());
            }
        }

        public static class StorageFailureTest extends AckGenericTest {
            @Override
            public void isStorageFailure(AckCode obj) {
                assertTrue(obj.isStorageFailure());
            }
        }

        public static void checkExtraPredicates(AckGenericTest test, AckResult obj) {
            test.isLimitMessages(obj);
            test.isLimitBytes(obj);
            test.isStorageFailure(obj);
        }

        public static void checkGeneric(AckResult obj) {
            AckResultTest.checkExtraPredicates(new AckGenericTest(), obj);
        }

        public static void checkLimitMessages(AckResult obj) {
            AckResultTest.checkExtraPredicates(new LimitMessagesTest(), obj);
        }

        public static void checkLimitBytes(AckResult obj) {
            AckResultTest.checkExtraPredicates(new LimitBytesTest(), obj);
        }

        public static void checkStorageFailure(AckResult obj) {
            AckResultTest.checkExtraPredicates(new StorageFailureTest(), obj);
        }

        @Test
        public void successTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.SUCCESS);
            GenericResultTest.checkSuccess(AckResult.SUCCESS);
            AckResultTest.checkGeneric(AckResult.SUCCESS);
        }

        @Test
        public void timeOutTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.TIMEOUT);
            GenericResultTest.checkTimeout(AckResult.TIMEOUT);
            AckResultTest.checkGeneric(AckResult.TIMEOUT);
        }

        @Test
        public void notConnectedTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.NOT_CONNECTED);
            GenericResultTest.checkNotConnected(AckResult.NOT_CONNECTED);
            AckResultTest.checkGeneric(AckResult.NOT_CONNECTED);
        }

        @Test
        public void canceledTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.CANCELED);
            GenericResultTest.checkCanceled(AckResult.CANCELED);
            AckResultTest.checkGeneric(AckResult.CANCELED);
        }

        @Test
        public void notSupportedTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.NOT_SUPPORTED);
            GenericResultTest.checkNotSupported(AckResult.NOT_SUPPORTED);
            AckResultTest.checkGeneric(AckResult.NOT_SUPPORTED);
        }

        @Test
        public void refusedTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.REFUSED);
            GenericResultTest.checkRefused(AckResult.REFUSED);
            AckResultTest.checkGeneric(AckResult.REFUSED);
        }

        @Test
        public void invalidArgumentTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.INVALID_ARGUMENT);
            GenericResultTest.checkInvalidArgument(AckResult.INVALID_ARGUMENT);
            AckResultTest.checkGeneric(AckResult.INVALID_ARGUMENT);
        }

        @Test
        public void notReadyTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.NOT_READY);
            GenericResultTest.checkNotReady(AckResult.NOT_READY);
            AckResultTest.checkGeneric(AckResult.NOT_READY);
        }

        @Test
        public void unknownTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.UNKNOWN);
            GenericResultTest.checkUnknown(AckResult.UNKNOWN);
            AckResultTest.checkGeneric(AckResult.UNKNOWN);
        }

        @Test
        public void limitMessagesTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.LIMIT_MESSAGES);
            AckResultTest.checkLimitMessages(AckResult.LIMIT_MESSAGES);
        }

        @Test
        public void limitBytesTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.LIMIT_BYTES);
            AckResultTest.checkLimitBytes(AckResult.LIMIT_BYTES);
        }

        @Test
        public void storageFailureTest() {
            GenericResultTest.checkIfNotGeneric(AckResult.STORAGE_FAILURE);
            AckResultTest.checkStorageFailure(AckResult.STORAGE_FAILURE);
        }
    }
}
