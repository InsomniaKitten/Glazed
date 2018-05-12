@file:JvmName("Entities")

package net.insomniakitten.glazed.extensions

/*
 *  Copyright 2018 InsomniaKitten
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import net.minecraft.entity.Entity
import net.minecraft.util.math.Vec3d

fun Entity.getPosition(partialTicks: Float) = Vec3d(
        lastTickPosX + (posX - lastTickPosX) * partialTicks,
        lastTickPosY + (posY - lastTickPosY) * partialTicks,
        lastTickPosZ + (posZ - lastTickPosZ) * partialTicks
)